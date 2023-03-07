using Distributions
using Plots

include("urand.jl")

# TODO: 1a)
uniform(N::Int)::Vector{Float64} = rand(N)

# TODO: 1b)
function mid_square(N::Int, seed::Int=34345669)::Vector{Float64}
    #gets digits and turns it to the middle number
    turnToNumber(d) = sum(d .* 10 .^ (0 : (length(d) - 1)))
    randomVec = []
    for _ in 1:N
        seed = turnToNumber(digits(seed * seed)[3:6])
        push!(randomVec, seed / 10000)
    end
    return randomVec
end

# TODO: 1c)
function halton(N::Int, base::Int=3)::Vector{Float64}
    M = zeros(Float64, N)
    for i in 1:N
        number = 0
        numberArr = digits(i, base=base)
        zeroArr = zeros(Int64, 6-length(numberArr))
        append!(numberArr, zeroArr)
        for j in 1:length(numberArr)
            number += numberArr[j]*(3.0^(-j))
        end
        M[i] = number
    end
    return M
    #(num -> sum(d * Float64(base) ^ -ex for (ex, d) in enumerate(digits(num, base=base)))).(1:N) 
end

# 1d)
uniform2D(N::Int)::Vector{Vector{Float64}} = [uniform(N), uniform(N)]

mid_square2D(N::Int, seed_x::Int=34123169, seed_y::Int=34345669)::Vector{Vector{Float64}} = [mid_square(N, seed_x), mid_square(N, seed_y)]

halton2D(N::Int, base_x::Int=3, base_y::Int=5)::Vector{Vector{Float64}} = [halton(N,base_x), halton(N,base_y)]

urand2D(N::Int)::Vector{Vector{Float64}} = [first(urand(2*N),N), last(urand(N*2),N)]

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
    legend=false,
    size=(1200, 800),
    layout=grid(4, 3)
)

savefig(p, "rng.pdf")
display(p)