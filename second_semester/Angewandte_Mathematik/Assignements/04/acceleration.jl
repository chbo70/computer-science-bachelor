using Plots


"Measured acceleration."
function acceleration(time::Number)::Number
    @assert(0 <= time <= 300, "Set time in interval [0,300]!")
    return time <= 60 ? 1.5 : time <= 150 ? 0 : -time/150 + 1
end


"True velocity."
function velocity(time::Number)::Number
    @assert(0 <= time <= 300, "Set time in interval [0,300]!")
    return time <= 60 ? 1.5*time : time <= 150 ? 90 : 90 - time^2/300 + time - 75
end


"True position."
function position(time::Number)::Number
    @assert(0 <= time <= 300, "Set time in interval [0,300]!")
    return time <= 60 ? 0.75*time^2 : time <= 150 ? 2700 + 90*time - 5400 : 10800 - time^3/900 + (time^2)/2 + 15*time - 9750
end


"Return numerical approximations."
function approximate_from(acc::Array, step::Number)
    return cumsum(acc*step), cumsum(cumsum(acc*step)*step)
end


"Main script pipeline."
function main()
    step = 1  # b) 1 c) 5
    time = 0:step:300
    acc = acceleration.(time)
    vel = velocity.(time)
    pos = position.(time)
    vel_app, pos_app = approximate_from(acc, step)

    plt1 = plot(time, acc, ylabel="a [m/s^2]", label="true", title="acceleration")
    plt2 = plot(time, vel, ylabel="v [m/s]", label="true", title="velocity")
    plot!(plt2, time, vel_app, label="approx")
    plt3 = plot(time, pos, ylabel="p [m]", xlabel="t [s]", label="true", title="position")
    plot!(plt3, time, pos_app, label="approx")

    plot_solutions = plot(plt1, plt2, plt3, layout=(3, 1))
    # savefig("/tmp/solutions.pdf")

    plt1 = plot(time, abs.(vel - vel_app), ylabel="v [m/s]", title="velocity error")
    plt2 = plot(time, abs.(pos - pos_app), ylabel="p [m]", xlabel="t [s]", title="position error")

    plot_err = plot(plt1, plt2, layout=(2, 1), legend=false)
    # savefig("/tmp/errors.pdf")
    plot(plot_solutions, plot_err, layout=(1, 2))
end


main()
