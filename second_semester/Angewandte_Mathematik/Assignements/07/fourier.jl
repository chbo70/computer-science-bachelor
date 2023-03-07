# package for plotting functions
using Plots
# use GR
gr()

f(x) = mod2pi(x) < pi ? -1.0 : 1.0

a_0() = 0 # TODO: enter your a_0
a(k) = 0 # TODO: enter your a_k
b(k) = 0 # TODO: enter your b_k 

function fourier(x,N)
    sum = a_0()
    for k in 1:N
        if mod(k,2) != 0
            a_term = (a(k)*cos(k*x))
            b_term = (b(k)*sin(k*x))
            sum += a_term + b_term
        end
    end
    sum
end

fourier_k_1(x) = fourier(x, 1)
fourier_k_2(x) = fourier(x, 2)
fourier_k_4(x) = fourier(x, 4)
fourier_k_8(x) = fourier(x, 8)
fourier_k_16(x) = fourier(x, 16)
fourier_k_32(x) = fourier(x, 32)

X = range(-2*pi, stop=2*pi, length=256)

plt = plot(X, f, color=:red, lw=1, label="squarewave" )
plot!(plt, X, fourier_k_1, lw=1, label="fourier squarewave N=1" )
plot!(plt, X, fourier_k_2, lw=1, label="fourier squarewave N=2" )
plot!(plt, X, fourier_k_4, lw=1, label="fourier squarewave N=4" )
plot!(plt, X, fourier_k_8, lw=1, label="fourier squarewave N=8" )
plot!(plt, X, fourier_k_16, lw=1, label="fourier squarewave N=16" )
plot!(plt, X, fourier_k_32, lw=1, label="fourier squarewave N=32" )

display(plot(plt))
