# packages
using FileIO;
using Plots;
pyplot();
using LinearAlgebra;
using Images;

# define color array & image size
window_width = 512;
window_height = 512;
color = zeros(3, window_width, window_height);

# define vertices - we treat z coordinate as 0 since we work in 2D
v_1 = [[0, 256, 0], [500, 32, 0], [500, 480, 0]];

# define colors
uv_1 = [[1.0, 0.0, 0.0], [0.0, 1.0, 0.0], [0.0, 0.0, 1.0]];

# barycentric interpolation
# return color for one point q
# arguments: vertices (v), point (q), colors we'd like to interpolate (uv)
function barycentric_interpolation(v, q, uv)
    # calculate vectors between vertices and q point use broadcasting iterate over vertices 
    vq1 = v[1] - q
    vq2 = v[2] - q
    vq3 = v[3] - q
    #get area of each triangle via crossproducts of vectors v_i_q
    area_1 = cross(vq1, vq2)
    area_2 = cross(vq2, vq3)
    area_3 = cross(vq3, vq1)
    #calculate area of main triangle and sum up 
    area_main = sum(area_1 + area_2 + area_3)
    # calculate barycentric coordinates using signed area of triangles
    # and area of main triangle
    barycentric_coordinates = [
        sum(area_2) / area_main,
        sum(area_3) / area_main,
        sum(area_1) / area_main
    ]
    #apply interpolation factors (barycentric coordinates) if q inside triangle
    if (barycentric_coordinates[1] > 0 && barycentric_coordinates[2] > 0 && barycentric_coordinates[3] > 0)
        #apply interpolation factors (barycentric coordinates) to colors (uv)
        color = uv[1] * barycentric_coordinates[1] + uv[2] * barycentric_coordinates[2] + uv[3] * barycentric_coordinates[3]
        return color
    else
        return [1.0, 1.0, 1.0]
    end
end

#=
# get area of each triangle via crossproducts of vectors v_i_q
    area_1 = cross(v_i_q[1], v_i_q[2]);
    area_2 = cross(v_i_q[2], v_i_q[3]);
    area_3 = cross(v_i_q[3], v_i_q[1]);
    #calculate area of main triangle and sum up 
    area = area_1[0] + area_2[0] + area_3[0];
    # calculate barycentric coordinates
    barycentric_coordinates = [dot(area_1, uv[1]), dot(area_2, uv[2]), dot(area_3, uv[3])];
    # if q is inside triangle, apply barycentric coordinates
    if (barycentric_coordinates[0] >= 0 && barycentric_coordinates[1] >= 0 && barycentric_coordinates[2] >= 0)
        return barycentric_coordinates;
    # if q is outside triangle, return black
    else
        return [0,0,0];
    end
=#

# iterate through points in triangle
# apply barycentric interpolation for each pixel (point q)
# returns color array filled with interpolated (color) values
function barycentric_interpolation_triangle(v, uv, x_max, y_max, c_array)
    for x = 1:x_max
        for y = 1:y_max
            c_array[:, x, y] = barycentric_interpolation(v, [x, y, 0], uv)
        end
    end
    return c_array
end

barycentric_interpolation_triangle(v_1, uv_1, window_height, window_width, color);

# convert color array to image
img = colorview(RGB, color);
# save image as PNG
save(File(format"PNG", joinpath(@__DIR__, "barycentric_interpolation_result.png")), img);