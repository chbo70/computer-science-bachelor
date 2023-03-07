function transpose(a::Array{Int64,2})
    (r,c) = size(a)

    transM = similar(a,(c,r))
    #transM = zeros(reverse(size(a)))
    for i in c
        for j in r
            transM[i,j] = a[j,i]
        end
    end
end

function transpose2(a::Array{Int64,2})
    (r,c) = size(a)
    b = zeros(reverse(size(a)))
    k = hcat(a[1,:])
    return [(hcat(b,a[i,:])) for i= 1:r]
end

function multiply(a::Array{Float64,2}, b::Array{Float64,2})
    (m,n) = size(a)
end





#for i in a
#for j in a
#if r < j
#continue
#else
#x = a[i,:]
#end
#end
#return x
#end