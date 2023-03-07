using Plots,Base

x = -6.0 : 0.01 : 5.0

fct_1(x) = x^2 + x + 1

fct_1_str = "f(x) = x^2 + x + 1"
fig_1 = plot(x,fct_1)


plt = plot(fig_1, title=fct_1_str, 
     titlefont = font(8), legend=false)

display(plt)
