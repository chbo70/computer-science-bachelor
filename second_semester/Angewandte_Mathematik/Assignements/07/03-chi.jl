# package for ploting functions
using Plots
# use GR
gr()



X = range(-5*π, stop=5*π, length=256)

#daniel rufinatschaaa helped a friendly chinese
# sin(x) approximation using Taylor series
function sinApprox(x, n)
        sum = 0.0
        for i in 0:n
                sum = sum + (-1)^i*x^(2*i+1)/factorial(big(2*i+1))
        end
	return sum				                                 ### <-- EXERCISE 5(b): implement the formula for the Taylor series of sin(x) given a value of n.
end


function error(x, n)
        abs(sin(x)-sinApprox(x, n))						### <-- EXERCISE 5(c): calculate absolute error between the n-th Taylor series and the real value of sin(x). 
end


# plot real sin(x)
plt1 = plot(X, sin, xlim = (-4*π, 4*π),  ylim = (-2, 2), color=:blue, lw = 3, label="sin(x)" );


n = 5;
# plot taylor series for n=5
plot!(plt1, X, sinApprox.(X, n), xlim = (-5*π, 5*π),  ylim = (-2, 2), color=:orange, lw = 3, label="n=5");
plot!(plt1, X, sinApprox.(X,15), xlim = (-5*pi, 5*pi), ylim = (-2,2), color=:black, lw = 3, label = "n=15");
plot!(plt1, X, sinApprox.(X,10), xlim = (-5*pi, 5*pi), ylim = (-2,2), color=:yellow, lw = 3, label = "n=10");

# plot error for n=5
plt2 = plot(X,  error.(X, n) , xlim = (-4*π, 4*π),  ylim = (-1, 2), color=:orange, label="error n=5" );
plot!(plt2, X,  error.(X, 10) , xlim = (-4*π, 4*π),  ylim = (-1, 2), color=:blue, label="error n=10" );
plot!(plt2, X,  error.(X, 15) , xlim = (-4*π, 4*π),  ylim = (-1, 2), color=:black, label="error n=15" );


# show plots
display( plot( plt1, plt2, layout = (2, 1) ))

