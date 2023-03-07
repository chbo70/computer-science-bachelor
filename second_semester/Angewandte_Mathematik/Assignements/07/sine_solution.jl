# package for ploting functions
using Plots
# use GR
gr()



X = range(-5*π, stop=5*π, length=256)


# sin(x) approximation using Taylor series
function sinApprox(x, n)
	sum(k-> (-1)^k*x^(2*k+1) / factorial(big(2*k+1)), 0:n);
end


function error(x, n)
	abs( sinApprox(x, n) - sin(x) );
end


# plot real sin(x)
plt1 = plot(X, sin, xlim = (-4*π, 4*π),  ylim = (-2, 2), color=:blue, lw = 3, label="sin(x)" );


n = 5;
# plot taylor series for n=5
plot!(plt1, X, sinApprox.(X, n), xlim = (-5*π, 5*π),  ylim = (-2, 2), color=:orange, lw = 3, label="n=5");
# plot error
plt2 = plot(X, error.(X, n), xlim = (-4*π, 4*π),  ylim = (-1, 2), color=:orange, label="error n=5" );

n = 10;
# plot taylor series for n=10
plot!(plt1, X, sinApprox.(X, n), xlim = (-5*π, 5*π),  ylim = (-2, 2), color=:red, lw = 3, label="n=10");
# plot error
plot!(plt2, X, error.(X, n), xlim = (-4*π, 4*π),  ylim = (-1, 2), color=:red, label="error n=10" );

n = 15;
# plot taylor series for n=15
plot!(plt1, X, sinApprox.(X, n), xlim = (-5*π, 5*π),  ylim = (-2, 2), color=:purple, lw = 3, label="n=15");
# plot error
plot!(plt2, X, error.(X, n), xlim = (-5*π, 5*π),  ylim = (-1, 2), color=:purple, label="error n=15" );



# show plots
display( plot( plt1, plt2, layout = (2, 1) ))

