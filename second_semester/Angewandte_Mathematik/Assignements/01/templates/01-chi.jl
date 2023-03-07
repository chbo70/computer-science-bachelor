#=
    Scalar/Scalar Multiplication = a)
    hint: you can use `exp(log(a)+log(b))` to multiply two positive float values
=#
function multiply(a::Float64, b::Float64)
  if a == 0|| b==0
    0
  elseif a < 0 && b < 0
    exp10(log10(abs(a)) + log10(abs(b)))
  elseif a < 0 || b < 0
    exp10(log10(abs(a)) + log10(abs(b))) * (-1)
  else
    exp10(log10(a) + log10(b))
  end
end

#= Scalar/Vector Multiplication = b)=#
function multiply(a::Float64, b::Array{Float64,1})
  #return[(multiply(a,i)) for i in b ]
  multiply.(a,b)
end

#= Scalar/Matrix Multiplication =#
function multiply(a::Float64, b::Array{Float64,2})
  #exp10.(broadcast(+, log10(a), broadcast(log10, b)))
   multiply.(a,b)
end


#= Dot Product = e)=#
function dot(r, c)
  #sum(multiply(r,c),multiply(r,c))
  sum(exp10.(broadcast(+, broadcast(log10,r), broadcast(log10, c))))
end

#broadcast applies the given function on arrays, vectors, ...
#with three arguments it applies the function between the two given arrays

function dot2(r, c)
  exp10.(broadcast(+, broadcast(log10,r), broadcast(log10, c)))
end


#= Vector/Matrix Multiplication = c)=#
function multiply(a::Array{Float64,1}, b::Array{Float64,2})
  r1 = length(a[:,1])
    #println(r1)
  (r2,c2) = size(b)
    #println((r2,c2))

  if r1 == c2
    return [round(dot(a[:,1],b[j,:])) for i in r2 for j in 1:r2]
  else
    return error("dimension mismatch")
  end
  
  #(sum(exp.(transpose((broadcast(+, log.(a), log.(transpose(b))))))),dims=2)
  #[sum((exp.(broadcast(+, broadcast(log,a), broadcast(log, b[1,:])))),dims=2)]
end


#println(multiply([1.0,2.5], [[2.0,3.0] [2.0,2.0]]))

#= Matrix/Matrix Multiplication = d)=#
function multiply(a::Array{Float64,2}, b::Array{Float64,2})
  (r1,c1) = size(a)
  (r2,c2) = size(b)
  c = zeros(Float64,r1,c2)
  
  if c1 != r2
    return error("dimensions mismatch")
  else
    for i in 1:r1
      for j in 1:c2
        sum = 0
        for k in 1:r2
          #println(sum)
          sum = sum + dot(a[i,k],b[k,j])
          #println(sum)
        end
        #println(sum)
        c[i,j] = sum
      end
    end
    return c
  end
#return[(multiply(x,y)) for (x,y) in zip(a,b)]
end



#println(multiply([[0.0, 0.0] [0.0, 0.0]], [[1.0,1.0] [1.0, 1.0]]))

#= Matrix/Matrix Addition = f)=#
function add(a::Array{Int,2}, b::Array{Int,2})
  map(+, a, b)
end


#= Matrix Transpose = g)=#
function transpose(a::Array{Int64,2})
  #transM = zeros(Int64,reverse(size(a)))
  (r,c) = size(a)
  transM = similar(a, (c,r))
  #println(transM)
  save = []
  
  for i in 1:c
      for j in 1:r
          transM[i,j] = a[j,i]
          #println(j)
      end
      save = transM
  end
  return save
end


#===============================================================================
        Test Cases (do not touch these!)
===============================================================================#
#= Scalar/Scalar Multiplication =#
@assert multiply(1.0, 2.0) == (1.0 * 2.0)
@assert multiply(1.0, 1.0) == (1.0 * 1.0)
@assert multiply(0.0, 1.0) == (0.0 * 1.0)
@assert multiply(-1.0, 2.0) == (-1.0 * 2.0)
@assert multiply(-1.0, 1.0) == (-1.0 * 1.0)
@assert multiply(-0.0, 1.0) == (-0.0 * 1.0)
@assert multiply(-1.0, -2.0) == (-1.0 * -2.0)
@assert multiply(-1.0, -1.0) == (-1.0 * -1.0)
@assert multiply(-0.0, -1.0) == (-0.0 * -1.0)
@assert multiply(1.0, -2.0) == (1.0 * -2.0)
@assert multiply(1.0, -1.0) == (1.0 * -1.0)
@assert multiply(0.0, -1.0) == (0.0 * -1.0)

#= Scalar/Vector Multiplication =#
@assert multiply(2.0, [1.0, 2.0]) == (2.0 * [1.0, 2.0])
@assert multiply(0.0, [1.0, 2.0]) == (0.0 * [1.0, 2.0])
@assert multiply(0.0, [1.0, 2.0]) != [0.0, 2.0]
@assert multiply(3.0, [3.0, 1.0]) == (3.0 * [3.0, 1.0])
@assert multiply(3.0, [3.0, 1.0]) == (3.0 * [3.0, 1.0])

#= Scalar/Matrix Multiplication =#
@assert multiply(0.0, [1.0 2.0]) == (0.0 * [1.0 2.0])
@assert multiply(2.0, [1.0 2.0]) == (2.0 * [1.0 2.0])

@assert multiply(1.0, [[1.0, 2.0] [1.0, 2.0]]) == (1.0 * [[1.0, 2.0] [1.0, 2.0]])
@assert multiply(1.0, [[1.0 2.0] [1.0 2.0]]) == (1.0 * [[1.0 2.0] [1.0 2.0]])
@assert multiply(2.0, [[1.0, 2.0] [1.0, 2.0]]) == (2.0 * [[1.0, 2.0] [1.0, 2.0]])
@assert multiply(2.0, [[1.0 2.0] [1.0 2.0]]) == (2.0 * [[1.0 2.0] [1.0 2.0]])
@assert multiply(0.0, [[1.0, 2.0] [1.0, 2.0]]) != [[0.0, 0.0] [1.0, 2.0]]
@assert multiply(0.0, [[1.0 2.0] [1.0 2.0]]) != [[0.0 0.0] [1.0 2.0]]

#= Dot Product =#
@assert dot([1.0, 1.0], [1.0, 1.0]) == 2.0
@assert dot([0.0, 1.0], [1.0, 1.0]) == 1.0
@assert dot([0.0, 0.0], [1.0, 1.0]) == 0.0
@assert dot([0.0, 0.0], [0.0, 0.0]) == 0.0
@assert dot([1.0 1.0], [1.0 1.0]) == 2.0
@assert dot([0.0 1.0], [1.0 1.0]) == 1.0
@assert dot([0.0 0.0], [1.0 1.0]) == 0.0
@assert dot([0.0 0.0], [0.0 0.0]) == 0.0
@assert dot([0.0, 2.0], [1.0, 2.0]) == 4.0
@assert dot([0.0, 2.0], [1.0, 1.0]) == 2.0

#= Vector/Matrix Multiplication =#
@assert multiply([0.0, 0.0], [[1.0, 1.0] [1.0, 1.0]]) == [0.0, 0.0]
@assert multiply([0.0, 2.0], [[1.0, 1.0] [1.0, 1.0]]) == [2.0, 2.0]
@assert multiply([1.0, 2.0], [[1.0, 1.0] [1.0, 1.0]]) == [3.0, 3.0]
@assert multiply([2.0, 2.0], [[1.0, 1.0] [1.0, 1.0]]) == [4.0, 4.0]
@assert multiply([0.0, 0.0], [[1.0, 2.0] [1.0, 1.0]]) == [0.0, 0.0]
@assert multiply([0.0, 2.0], [[1.0, 2.0] [1.0, 1.0]]) == [2.0, 2.0]
@assert multiply([1.0, 2.0], [[1.0, 2.0] [1.0, 1.0]]) == [3.0, 4.0]
@assert multiply([2.0, 2.0], [[1.0, 2.0] [1.0, 1.0]]) == [4.0, 6.0]

#= Matrix Transpose =#
@assert transpose([[0] [0]]) == [0 0]'
@assert transpose([[1] [2]]) == [1 2]'
@assert transpose([[1, 1] [2, 2]]) == [1 2; 1 2]'
@assert transpose([[1, 2] [3, 4]]) == [1 3; 2 4]'
@assert transpose([[1, 1, 1, 1] [2, 2, 2, 2]]) == [1 2; 1 2; 1 2; 1 2]'
@assert transpose([[1] [2] [3] [4]]) == [1 2 3 4]'

#= Matrix/Matrix Multiplication =#

@assert multiply([[0.0, 0.0] [0.0, 0.0]], [[1.0, 1.0] [1.0, 1.0]]) == [[0.0, 0.0] [0.0, 0.0]]
@assert multiply([[0.0, 0.0] [1.0, 1.0]], [[1.0, 1.0] [1.0, 1.0]]) == [[1.0, 1.0] [1.0, 1.0]]
@assert multiply([[1.0, 1.0] [1.0, 1.0]], [[1.0, 1.0] [1.0, 1.0]]) == [[2.0, 2.0] [2.0, 2.0]]
@assert multiply([[0.0, 0.0] [0.0, 1.0]], [[1.0, 1.0] [1.0, 1.0]]) == [[0.0, 1.0] [0.0, 1.0]]
@assert multiply([[0.0, 1.0] [0.0, 1.0]], [[1.0, 1.0] [1.0, 1.0]]) == [[0.0, 2.0] [0.0, 2.0]]
@assert multiply([[1.0, 0.0] [1.0, 0.0]], [[1.0, 1.0] [1.0, 1.0]]) == [[2.0, 0.0] [2.0, 0.0]]

#= Matrix/Matrix Addition =#
@assert add([[9, 8, 7] [6, 5, 4] [3, 2, 1]],
  [[1, 2, 3] [4, 5, 6] [7, 8, 9]]) == [[9, 8, 7] [6, 5, 4] [3, 2, 1]] + [[1, 2, 3] [4, 5, 6] [7, 8, 9]]
@assert add([[-1, -2, -3] [-4, -5, -6] [-7, -8, -9]],
  [[1, 2, 3] [4, 5, 6] [7, 8, 9]]) == [[-1, -2, -3] [-4, -5, -6] [-7, -8, -9]] + [[1, 2, 3] [4, 5, 6] [7, 8, 9]]


