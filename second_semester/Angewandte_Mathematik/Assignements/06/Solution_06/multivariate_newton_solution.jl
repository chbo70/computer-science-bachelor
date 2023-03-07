using Plots

function multivariate_newton(func::Function, jacobian::Function, x_0::Vector, iter_max::Int=16)::Vector
    x_1 = x_0
    iter = 0
    converged = false

    while !converged && iter < iter_max
        Δx = -jacobian(x_0)\func(x_0)
        x_1 += Δx
        x_0 = x_1
        converged = sum(func(x_1).^2) < eps()
        
        iter += 1
    end
    return x_1
end

# students should implement these
"Jacobian of function f."
function jacobian_f(x)
    return [2*x[1] 0;
            0 2*x[2]]
end


"Jacobian of function g."
function jacobian_g(x)
    return [3*x[1]^2*x[2]^2-x[2]^3 2*x[1]^3*x[2]-3*x[1]*x[2]^2;
        2*x[1]-x[2]^3 -3*x[1]*x[2]^2]
    
end


"Jacobian of function h."
function jacobian_h(x)
    return [4*x[1] x[3]*sin(x[2]*x[3]) x[2]*sin(x[2]*x[3]);
            8*x[1] -3*420*x[2]^2 4;
            -x[2]*exp(-x[1]*x[2]) -x[1]*exp(-x[1]*x[2]) 20]
end

# given functions

function f(x)
    return [x[1]^2; x[2]^2]
end


function g(x)
    return [x[1]^3*x[2]^2 - x[1]*x[2]^3 - 1; x[1]^2 - x[1]*x[2]^3 - 4]
end

function h(x)
    return [2*x[1]^2 - cos(x[2]*x[3]) - 3/2; 4*x[1]^2 - 420*x[2]^3 + 4*x[3] - 1; 20*x[3] + exp(-x[1]*x[2]) + 10]
end