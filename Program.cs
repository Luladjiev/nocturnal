using FluentValidation;
using nocturnal.Db;
using nocturnal.DTO;
using nocturnal.Extensions;
using nocturnal.Routes;
using nocturnal.Services;
using nocturnal.Validators;

var builder = WebApplication.CreateBuilder(args);

var connection = builder.Configuration.GetConnectionString("NocturnalDb") ?? "Data Source=nocturnal.db";
builder.Services.AddSqlite<NocturnalDb>(connection);

builder.Services.AddNocturnalOpenApi();

// Validators
builder.Services.AddScoped<IValidator<CreateProjectDto>, ProjectValidator>();

// Services
builder.Services.AddScoped<ProjectsService>();

var app = builder.Build();

if (app.Environment.IsDevelopment())
    app.UseNocturnalOpenApiUi();

app.UseDefaultFiles();
app.UseStaticFiles();

app.UseStatusCodePages(async context =>
{
    var statusCode = context.HttpContext.Response.StatusCode;
    var path = context.HttpContext.Request.Path;

    switch (statusCode)
    {
        case 404 when path.StartsWithSegments("/api"):
            await context.HttpContext.Response.WriteAsJsonAsync(new { code = 404, message = "Not Found" });
            break;
        case 404:
            await context.HttpContext.Response.SendFileAsync(Path.Combine("wwwroot", "index.html"));
            break;
        case 500:
            await context.HttpContext.Response.SendFileAsync(Path.Combine("wwwroot", "500.html"));
            break;
    }
});

var api = app.MapGroup("/api");

api.MapProjectsRoutes();

app.Run();