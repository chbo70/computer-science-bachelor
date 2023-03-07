using Distributions
using Plots

# Parameters
f(x) = exp(-(x^2))

a = 0.0 
b = 1.0

# TASKS
# 2a)
function power_series(a::Float64, b::Float64)::Float64
   N = 5
   sum([((-a ^ 2) ^ k) / factorial(big(k)) for k in 0 : N]) - sum([((-b ^ 2) ^ k) / factorial(big(k)) for k in 0 : N])
end

scale(sequence::Vector{Float64}, a::Float64=0.0, b::Float64=1.0)::Vector{Float64} = map(x -> x*(b-a)+a, sequence)

# 2b)
function mc_integration(a::Float64, b::Float64, N::Int, sequence::Vector{Float64})::Float64
    #monte carlo integration using the sequence
    sequence = scale(sequence,a,b)
    sum = 0.0
    for i in 1:N
        sum = sum + f(sequence[i])
    end
    return (b-a) * sum / N
end

# 2c)
function mc_integration_by_darts(a::Float64, b::Float64, N::Int, sequence2D)::Float64
    #monte carlo integration by darts 
    #generate random Points in the interval
    sequence = scale(sequence2D[1],a,b)
    sequence2D = scale(sequence2D[2],a,b)
    sum = 0.0
    for i in 1:N
        sum = sum + f(sequence2D[i])
    end
    return (b-a) * sum / N
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