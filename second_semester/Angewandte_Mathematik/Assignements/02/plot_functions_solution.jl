using Plots

function plot_function(fct, x_min, x_max, step_size)
    # TODO 
    # Hint: map function could be useful

    x = range(x_min, x_max, step= step_size)
    plot(x,map(fct,x))
end



# Using the example plots here, plot all six given functions in a nice layout.
# TODO
fct_1(x) = 3*x^2 - x - 7
fct_1_str = "f(x) = x^2 + x + 1"
fig_1 = plot_function(fct_1, -7.0, 7.0, 0.01)

fct_2(x) = (7/5)^x - 1/2*x^3 
fct_2_str = "f(x) =(7/5)^x - 1/2*x^3"
fig_2 = plot_function(fct_2, -7.0, 7.0, 0.01)

fct_3(x) = 3*sin(x) + cos(10*x)*1/3*sin(x)
fct_3_str = "f(x) = 3*sin(x) + cos(10*x)*1/3*sin(x)"
fig_3 = plot_function(fct_3, -7.0, 7.0, 0.01)

fct_4(x) = abs(abs(abs(abs(x)-1)-1)-1)
fct_4_str = "f(x) = ||||x-|1|-1|-1|"
fig_4 = plot_function(fct_4, -7.0, 7.0, 0.01)

fct_5(x) = 1/x
fct_5_str = "f(x) = 1/x"
fig_5 = plot_function(fct_5, -3.0, 3.0, 0.01)

fct_6(x) = log(abs(x))
fct_6_str = "f(x) = log|x|"
fig_6 = plot_function(fct_6, -5.0, 5.0, 0.01)
 

plot(fig_1, fig_2, fig_3, fig_4, fig_5, fig_6, layout=(3,2), 
    title=[fct_1_str fct_2_str fct_3_str fct_4_str fct_5_str fct_6_str], 
     titlefont = font(8), legend=false) 