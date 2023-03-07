using Distributions
using Plots

N = 4096*2 

U(a,b) = rand(Uniform(a,b), N)

module Example
    a=0
    b=1
    f="sin(x)/2"
    pdf(x) = sin(x)/2
    cdf(x) = (-cos(x)+1)/2
    inverse(x) = acos(1-2*x)
end
module A
    a = 0
    b = 1 
    f = "sin(x)"
    pdf(x) = sin(x)
    cdf(x) = 1-cos(x) 
    inverse(x) = acos(1-x)
end

module B
    a = 0
    b = 1
    f = "3*x^2"
    pdf(x) = 3*x^2 
    cdf(x) = x^3 
    inverse(x) = cbrt(x)
end

module C
    a = 0
    b = 1 
    f = "exp(x)"
    pdf(x) = exp(x) 
    cdf(x) = exp(x)-1 
    inverse(x) = log(x+1) 
end

# plot helper
function plot_transformation(a,b,pdf,cdf,inverse,title)
    dist = U(a,b)
    i = inverse.(dist)
    x = minimum(i):10^-3:maximum(i)
    plot(
        plot(x, pdf.(x), title=title),
        plot(x, cdf.(x), title="f_CDF(x)"),
        histogram(i, title="transformation"),
        histogram(cdf.(i), title="re-transformation"),
        legend = false,
        layout = grid(1,4),
        size = (1200,300),
    )
end

modules = [ Example, A, B, C ]
plots = [plot_transformation(m.a,m.b,m.pdf,m.cdf,m.inverse,m.f) for m = modules]

# display distribution
nPlots = length(modules)
p = plot(plots..., layout = grid(nPlots,1), size = (1200, nPlots*300))
savefig(p,"its.pdf")
display(p)
