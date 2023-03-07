using Distributions
using Plots

include("urand.jl")

# 1a)
uniform(N::Int)::Vector{Float64} = rand(Uniform(0,1),N)

# 1b)
function mid_square(N::Int, seed::Int=34345669)::Vector{Float64}
    digits_to_number(d) = sum(d.*10 .^(0:(length(d)-1)))
    number_to_digits(n) = digits(n)[3:6] 
	a = []
	for _ in 1:N
		seed = digits_to_number(number_to_digits(seed*seed))
		push!(a, seed/10000)
	end
	a
end

# 1c)
function halton(N::Int, base::Int=3)::Vector{Float64}
    (num -> sum(d * Float64(base) ^ -ex for (ex, d) in enumerate(digits(num, base=base))) ).(1:N)
end

# 1d)
uniform2D(N::Int)::Vector{Vector{Float64}} = [uniform(N), uniform(N)]

mid_square2D(N::Int, seed_x::Int=34123169, seed_y::Int=34345669)::Vector{Vector{Float64}} = [mid_square(N, seed_x), mid_square(N, seed_y)]

halton2D(N::Int, base_x::Int=3, base_y::Int=5)::Vector{Vector{Float64}} = [halton(N, base_x), halton(N, base_y)]

urand2D(N::Int)::Vector{Vector{Float64}} = [urand(N), urand(N*2)[N+1:N*2]]

# display
N = 256

# 1D
sequence_uniform = uniform(N)
sequence_mid_square = mid_square(N)
sequence_halton = halton(N)
sequence_urand = urand(N)

# 2D
sequence_uniform2D = uniform2D(N)
sequence_mid_square2D = mid_square2D(N)
sequence_halton2D = halton2D(N)
sequence_urand2D = urand2D(N)

p = plot(
    scatter(sequence_uniform, title="Julia's Uniform rand() 1D"),
    histogram(sequence_uniform, title="Distribution"),
    scatter(sequence_uniform2D[1], sequence_uniform2D[2], title="Julia's Uniform rand() 2D"),
    scatter(sequence_mid_square, title="Middle-square 1D"),
    histogram(sequence_mid_square, title="Distribution"),
    scatter(sequence_mid_square2D[1], sequence_mid_square2D[2], title="Middle-square 2D"),
    scatter(sequence_halton, title="Halton Sequence 1D"),
    histogram(sequence_halton, title="Distribution"),
    scatter(sequence_halton2D[1], sequence_halton2D[2], title="Halton Sequence 2D"),
    scatter(sequence_urand, title="Urand Sequence 1D"),
    histogram(sequence_urand, title="Distribution"),
    scatter(sequence_urand2D[1], sequence_urand2D[2], title="Urand Sequence 2D"),
    legend = false,
    size = (1200,800), 
    layout = grid(4,3)
)

#savefig(p, "rng.pdf")
display(p)