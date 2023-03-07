# packages
using FileIO;
using Plots; pyplot();
using LinearAlgebra;
using Images;

# define color array & image size
window_width = 512;
window_height = 512;
color = zeros(3,window_width,window_height);

# define vertices - we treat z coordinate as 0 since we work in 2D
v_1 = [[0,256,0],[500,32,0],[500,480,0]];

# define colors
uv_1 = [[1.0, 0.0, 0.0], [0.0, 1.0, 0.0], [0.0, 0.0, 1.0]];

# barycentric interpolation (general - we also consider the sign)
# return color for one point q
function barycentric_interpolation(v, q, uv)
    # calculate vectors between vertices and q
    qv1 = v[1] - q;
    qv2 = v[2] - q;
    qv3 = v[3] - q;
    # get area of each triangle via crossproducts
    c_a_main = cross(v[1]-v[2], v[1]-v[3]);
    c_a_1 = cross(qv2, qv3);
    c_a_2 = cross(qv3, qv1);
    c_a_3 = cross(qv1, qv2);
    # main triangle area
    a = norm(c_a_main); 
    # barycentric coordinates with sign
    a1 = norm(c_a_1)/a * sign(dot(c_a_main, c_a_1));
    a2 = norm(c_a_2)/a * sign(dot(c_a_main, c_a_2));
    a3 = norm(c_a_3)/a * sign(dot(c_a_main, c_a_3));
    # test if q is inside the triangle
    if (a1 >= 0.0 && a1 <= 1.0) && (a2 >= 0.0 && a2 <= 1.0) && (a3 >= 0.0 && a3 <= 1.0)
        # apply interpolation factors
        new_color_q = uv[1] * a1 + uv[2] * a2 + uv[3] * a3;
    else
        new_color_q = [1.0 1.0 1.0];
    end
    return new_color_q
end

# iterate through points in triangle
# apply barycentric interpolation for each pixel (point q)
# returns color array filled with interpolated (color) values
function barycentric_interpolation_triangle(v, uv, x_max, y_max, c_array)
    for x = 1:x_max
        for y = 1:y_max
            c_array[:,x,y] = barycentric_interpolation(v, [x,y,0], uv);
        end
    end
    return c_array;
end

barycentric_interpolation_triangle(v_1, uv_1, window_height, window_width, color);

# convert color array to image
img = colorview(RGB, color);
# save image as PNG
save(File(format"PNG", joinpath(@__DIR__, "barycentric_interpolation_result.png")), img);