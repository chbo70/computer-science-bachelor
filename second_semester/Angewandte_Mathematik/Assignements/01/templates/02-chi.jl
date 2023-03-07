using Plots

t = -0.012 : pi/90000 : 0.012;

A = 1

f1 = 329.63 #note e
f2 = 415.30 #note G#
f3 = 493.88 #note B

#functions:
E = t .+  A * sin.(2*pi*f1*t);
G = t .+ A * sin.(2*pi*f2*t);
B = t .+ A * sin.(2*pi*f3*t);
triad = t .+ E + G + B;

#plot
plt = plot(t,
    E, 
    color = :black, 
    linewidth = 0.5,
    label = "E = 329.63 Hz",
    title = "Triad sound wave",
    xlabel = "t (seconds)",
    ylabel = "y(t)",
    ylims = (-3, 3)
    );

plot!(plt, 
    t,
    G, 
    color = :lightblue, 
    linewidth = 0.5,
    label = "G# = 415.30 Hz"
    );

plot!(plt, 
    t, 
    B, 
    color = :green, 
    linewidth = 0.5,
    label = "B = 493.88 Hz"
    );
plot!(plt,
    t,
    triad,
    color = :darkblue,
    linewidth = 1.5,
    label = "triad"
    )

#p = plot( 0:0.1:2π, sin, label="sine", title="Trigonometry Functions" )
#plot!( 0:0.1:2π,  cos, label="cosine")

display(plt)
