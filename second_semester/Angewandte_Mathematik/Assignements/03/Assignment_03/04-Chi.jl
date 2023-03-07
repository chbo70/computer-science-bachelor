using Plots


# 4a
f = (x, y) -> 3*x^2 + (x+2*y)^2 + 5*(2*x-y)^2 + (x-y-3)^2 + 2*y^2
f_gradient=(x,y)->[
    50*x - 18*y - 6,
    -18*x + 24*y + 6
]

g = (x, y) -> (cos(x) + sin(y))/2
g_gradient = (x,y) -> [
    -0.5 * sin(x),
    0.5 * cos(y)
]

h = (x, y)-> (8*x^2 + 6*y^2 - 1)*ℯ^(-x^2-y^2)
h_gradient =(x, y)-> [
    16*x*ℯ^((-x^2)-y^2) - 2*x*(8*x^2 + 6*y^2 - 1),
    12*x*ℯ^((-x^2)-y^2) - 2*y* (8*x^2 + 6*y^2 - 1)
]



# 4b
function update_step(x::Array{Float64, 1}, func_grad, gamma::Float64)::Array{Float64, 1}
    x - gamma * func_grad(x[1],x[2])    
end

function gradient_descent(start::Array{Float64, 1}, func_grad, gamma::Float64, iter::Integer)
    path = zeros((iter, 2))

    x = start
    for i = 1:iter
        path[i, :] = x
        x = update_step(x, func_grad, gamma)
    end

    path
end


f_results = gradient_descent([1.5, 1.5], f_gradient, 0.01, 100)
g_results = gradient_descent([6.0, 1.0], g_gradient, 0.01, 1000)
h_results = gradient_descent([0.5, 0.5], h_gradient, 0.01, 100)
c_results = gradient_descent([acos(1), π / 2], g_gradient, 0.01, 1000) 

function plot_results(func, path, axis, label)
    pSurface = Plots.surface(axis, axis, func, camera=(45, 45), c = :heat, xlabel = "x", ylabel= "y", title=label, colorbar = false)
    plot!(pSurface, path[:, 1], path[:, 2], func.(path[:, 1], path[:, 2]), color = :blue, linewidth=2, label="search path")
    scatter!(pSurface, [path[1, 1]], [path[1, 2]], [func(path[1, :]...)], markersize=7, color=:blue, label="start")
    scatter!(pSurface, [path[end, 1]], [path[end, 2]], [func(path[end, :]...)], markersize=7, color=:red, label="found minimum")

    pHeatmap = heatmap(axis, axis, func, c = :heat, xlabel = "x", ylabel= "y", legend=false, colorbar=true)
    contour!(pHeatmap, axis, axis, func)
    plot!(pHeatmap, path[:, 1], path[:, 2], linewidth=3, color = :blue)
    scatter!(pHeatmap, [path[1, 1]], [path[1, 2]], markersize=7, color=:blue)
    scatter!(pHeatmap, [path[end, 1]], [path[end, 2]], markersize=7, color=:red)

    combined = plot(pSurface, pHeatmap, layout = (1, 2))
    combined
end;


pf = plot_results(f, f_results, -2:0.1:2, "f(x, y)")
plot!(size=(1200, 480))
savefig(pf, "ex_grad_descent_f.png")

pg = plot_results(g, g_results, -4:0.1:10, "g(x, y)")
plot!(size=(1200, 480))
savefig(pg, "ex_grad_descent_g.png")

ph = plot_results(h, h_results, -4:0.1:5, "h(x, y)")
plot!(size=(1200, 480))
savefig(ph, "ex_grad_descent_h.png")

# 4c
# no since it could be maximum or minimum , if we use these parameters we will get a local maximum
pc = plot_results(g, c_results, -4:0.1:10, "gc(x,y)")
plot!(size=(1200,480))
savefig(pc, "ex_grad_descent_c.png")