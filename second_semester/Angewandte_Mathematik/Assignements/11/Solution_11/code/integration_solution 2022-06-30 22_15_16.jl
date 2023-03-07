using Distributions
using Plots

# Parameters
f(x) = exp(-(x^2))

a = 0.0 
b = 1.0

# TASKS
# 2a)
function power_series(a::Float64, b::Float64)::Float64
    f_approx(x, k) = ((-1)^k * x^(2*k+1)) / ((2*k+1)*factorial(big(k)))
    sum_a = 0
    sum_b = 0
    for k = 0:100
        sum_a = sum_a + f_approx(a, k) 
        sum_b = sum_b + f_approx(b, k) 
    end
    sum_b - sum_a
end

scale(sequence::Vector{Float64}, a::Float64=0.0, b::Float64=1.0)::Vector{Float64} = map(x -> x*(b-a)+a, sequence)

# 2b)
function mc_integration(a::Float64, b::Float64, N::Int, sequence::Vector{Float64})::Float64
    # HINT: halton::Vector{::Float64} -> 4096 pairs of x values
    y = map(x -> f(x), sequence[1:N])
    s = sum(y)
    (b-a)/float(N)*s
end

# 2c)
function mc_integration_by_darts(a::Float64, b::Float64, N::Int, sequence2D)::Float64

    # compute y at random x 
    y_value = f.(sequence2D[1][1:N]) 

    # For the purpose of this exercise you can assume that f(x) > 0 for all x
    # check if f(x) is in/out
    inCount = 0 
    for (value, y_random) in zip(y_value, sequence2D[2][1:N])
        if (value >= y_random)
            inCount+=1
        end
    end
    (b-a)*inCount/N
end

# RESULTS
module Uniform
    name = "rand()"
    distribution = include("integration_uniform_1.jl")
    distribution2D = include("integration_uniform_2.jl")
end

module MiddleSquare
    name = "MiddleSquare"
    distribution = include("integration_mid_square_1.jl")
    distribution2D = include("integration_mid_square_2.jl")
end

module Halton
    name = "Halton"
    distribution = include("integration_halton_1.jl")
    distribution2D = include("integration_halton_2.jl")
end

module Urand
    name = "urand"
    distribution = include("integration_urand_1.jl")
    distribution2D = include("integration_urand_2.jl")
end

x = collect(a-1:(b+1)/256:b+1)
y = f.(x)
wolframalpha = 0.746824 
solution_power_series = power_series(a, b)


modules = [Uniform, MiddleSquare, Halton, Urand]
plot_list = [ ] 
for m = modules
    solution_mc_integration = mc_integration(a, b, 256, scale(m.distribution, a, b))
    m.distribution2D[1] = scale(m.distribution2D[1], a, b)
    solution_mc_integration_by_darts = mc_integration_by_darts(a, b, 256, m.distribution2D)

    println(m.name, "-----------------------------------------------")
    println("Wolframalpha Solution: \t\t\t", wolframalpha)
    println("Power Series Solution: \t\t\t", solution_power_series)
    println("MC Integration Solution: \t\t", solution_mc_integration)
    println("MC Integration by Darts Solution: \t", solution_mc_integration_by_darts)

    # PLOTTING

    p = plot(x, y, label="f(x)", title=m.name)
    scatter!(m.distribution[1:32], f.(m.distribution[1:32]), label="f(x) x in X")
    scatter!(m.distribution2D[1][1:128], m.distribution2D[2][1:128], label="(x,y) 2D")
    append!(plot_list, [p])
end

p = plot(plot_list..., size = (1200,1200))
#savefig(p, "integration.pdf")
display(p)