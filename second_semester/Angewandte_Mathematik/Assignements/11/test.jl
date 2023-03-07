using Distributions
using Plots
include("urand.jl")
#===============================================================================
Uniform distribution by julia
===============================================================================#

# 1D version
# TODO: 2d)
function uniform(N)
    rand(Uniform(0,1),N)
end

# 2D version
# TODO: 2f)
function uniform2D(N)
    [ uniform(N), uniform(N) ]
end

#===============================================================================
HALTON SEQUENCE
===============================================================================#

# 1D version
# TODO: 2c)
function halton(base, N)
    (num -> sum(d * Float64(base) ^ -ex for (ex, d) in enumerate(digits(num, base=base))) ).(1:N)
end

# 2D version
# TODO: 2f)
function halton2D(base_x, base_y, N)
    [ halton(base_x, N), halton(base_y, N) ]
end

#===============================================================================
MID SQUARE
===============================================================================#

# 1D version
# TODO: 2b)
function mid_square(seed, N)

    digits_to_number(d) = sum(d.*10 .^(0:(length(d)-1)))
    number_to_digits(n) = digits(n)[3:6]

	a = []
	for _ in 1:N
		seed = digits_to_number(number_to_digits(seed*seed))
		push!(a, seed/10000)
	end
	a
end

# 2D version
# TODO: 2f)
function mid_square2D(seed_x, seed_y, N)
    [ mid_square(seed_x, N), mid_square(seed_y, N) ]
end

#===============================================================================
numbers from /dev/URAND
===============================================================================#

# 1D version - just import the sequence called "urand"
# 2d)

# 2D version
# TODO: 2f)
function urand2D(N)
    [ urand[1:N], urand[N+1:N*2] ]
end

#===============================================================================
PARAMETERS
===============================================================================#
f(x) = sin(x)

a = 0
b = pi
N = 204800

# TODO: 2a)
answer = 2

error(a) = abs(a-answer)
function result(name, answer)
    println("--------------------------")
    println(name)
    println(answer)
    println(error(answer))
    println("--------------------------")
end

scale(a,b,sequence) = map(x -> x*(b-a)+a,sequence)

#===============================================================================
Monte Carlo Integration - average function value version
===============================================================================#
# TODO: 2d)
function mc_integration(a, b, sequence, N)
    sequence = scale(a,b,sequence)
    y = map(x -> f(x), sequence)
    s = sum(y)
    i = (b-a)/float(N)*s
    sequence, y, i
end

# generate samples
sequence_m, y_m, answer_m = mc_integration(a, b, mid_square(34345669, N), N)
sequence_j, y_j, answer_j = mc_integration(a, b, uniform(N), N)
sequence_h, y_h, answer_h = mc_integration(a, b, halton(3,N), N)
sequence_u, y_u, answer_u = mc_integration(a, b, urand[1:N], N)

println("==========================")
println("Integration Average Function Value")
println("==========================")
result("Mid-square", answer_m)
result("Julia's Uniform rand():", answer_j)
result("Halton Sequence:", answer_h)
result("/dev/urand:", answer_u)

#===============================================================================
Monte Carlo Integration - integration by darts
===============================================================================#
# TODO: 2f)
# NOTE: this solution is more generic than what was required from you
function mc_integration_by_darts(a, b, sequence, N)

    sequence[1] = scale(a, b, sequence[1])

    # lowest/highest f(x)
    min_y = minimum(f.(a:10^-7:b))
    max_y = maximum(f.(a:10^-7:b))

    sequence[2] = scale(min_y, max_y, sequence[2])

    # compute function bounds at random x
    y = map(x -> f(x), sequence[1])

    # check if point is in/out: 0 for out, 1 and -1 for in
    inout = []
    for (y_v, y_r) in zip(y,sequence[2])
        if (y_v > 0 && y_r > 0)
            push!(inout, y_r < y_v ? 1 : 0)
        elseif (y_v < 0 && y_r < 0)
            push!(inout, y_r > y_v ? -1 : 0)
        else
            N-=1
        end
    end

    # sum points in area and divide by total points
    i = (b-a)*sum(inout)/N

    sequence, i
end

# generate samples
samples_m2D, answer_m2D = mc_integration_by_darts(a, b, mid_square2D(34123169,34345669, N), N)
samples_j2D, answer_j2D = mc_integration_by_darts(a, b, uniform2D(N), N)
samples_h2D, answer_h2D = mc_integration_by_darts(a, b, halton2D(2,3,N), N)
samples_u2D, answer_u2D = mc_integration_by_darts(a, b, urand2D(N), N)

println("==========================")
println("Integration by Darts")
result("Middle-square:", answer_m2D)
result("Julia's Uniform rand():", answer_j2D)
result("Halton Sequence:", answer_h2D)
result("/dev/urand:", answer_u2D)

#===============================================================================
PLOTTING
===============================================================================#
x = collect(a-1:(b+1)/N:b+1)
y = map(x_i -> f(x_i), x)

# integration
function plot_integration(x, y, samples, fx)
    p = plot(x, y, title="Function Samples", legend=false)
    scatter!(samples[1:64], fx[1:64])
    p
end

plot_m = plot_integration(x, y, sequence_m, y_m)
plot_j = plot_integration(x, y, sequence_j, y_j)
plot_h = plot_integration(x, y, sequence_h, y_h)
plot_u = plot_integration(x, y, sequence_u, y_u)

# integration by darts
function plot_integration_darts(x, y, samples)
    p = plot(x, y, title="Integration by Darts", legend=false)
    scatter!(samples[1][1:64], samples[2][1:64])
    p
end

plot_m2D = plot_integration_darts(x, y, samples_m2D)
plot_j2D = plot_integration_darts(x, y, samples_j2D)
plot_h2D = plot_integration_darts(x, y, samples_h2D)
plot_u2D = plot_integration_darts(x, y, samples_u2D)

# display
plot(
    scatter(sequence_m[1:64], title="Middle-square 1D"),
    histogram(sequence_m, title="Distribution"),
    plot_m,
    plot_m2D,
    scatter(sequence_j[1:64], title="Julia's Uniform rand() 1D"),
    histogram(sequence_j, title="Distribution"),
    plot_j,
    plot_j2D,
    scatter(sequence_h[1:64], title="Halton Sequence 1D"),
    histogram(sequence_h, title="Distribution"),
    plot_h,
    plot_h2D,
    scatter(sequence_u[1:64], title="/dev/urand 1D"),
    histogram(sequence_u, title="Distribution"),
    plot_u,
    plot_u2D,
    legend = false,
    size = (1200,800),
    layout = grid(4,4)
)
end