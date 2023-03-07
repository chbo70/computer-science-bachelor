using Plots


# 3a
f(x) = cos(x)
f_prime(x)=-sin(x)

g(x) = x^2+2*cos(x) - 5
g_prime(x) = 2*x-2*sin(x)

h(x) = 2*(x^2)*(ℯ^x) + 2*(x^3)
h_prime(x) = 2*(x^2)*(ℯ^x) +4*x*(ℯ^x) +6*(x^2)

function newton_method(func, func_prime, start::Float64, iter::Integer)::Float64
    x = start

    for start in 0:iter
        x = x - func(x)/func_prime(x)  # 3a
    end

    x
end;


function plot_results(func, x, start, root, label)
    pLine = plot(x, func, xlabel="x", ylabel="y", label="", legend=:bottomright, title=label)
    plot!(pLine, [0], seriestype="hline", label="")
    scatter!(pLine, [start], [func(start)], color=:blue, label="start")
    scatter!(pLine, [root], [func(root)], color=:red, label="root")
end;


f_root = newton_method(f, f_prime, -pi/3, 10)
g_root = newton_method(g, g_prime, -1.0, 10)
h_root = newton_method(h, h_prime, -1/2, 10)


pf = plot_results(f, -3:0.1:0, -pi/3, f_root, "f(x)")
pg = plot_results(g, -4:0.1:-1, -1, g_root, "g(x)")
ph = plot_results(h, -1:0.1:1, -1/2, h_root, "h(x)")


p = plot(pf, pg, ph, layout=(1, 3))
plot!(size=(1200, 480))
savefig(p, "ex_newton.png")

#3b)
#newton_method(f, f_prime, 0.0, 10) 
#=
starting with 0, would not work in this function since an extremum is at 0, 
which means it would end in an endless loop

3c)
In order to find the local minimum we could set the derivations of a function in newtons method equal 0,
since this will be returning us all the pounts where the gradient is 0 => extrema

=#