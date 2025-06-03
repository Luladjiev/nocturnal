using Microsoft.AspNetCore.Http.HttpResults;
using nocturnal.DTO;
using nocturnal.Filters;
using nocturnal.Services;

namespace nocturnal.Routes;

public static class Projects
{
    public static void MapProjectsRoutes(this RouteGroupBuilder group)
    {
        var projects = group.MapGroup("/projects");

        projects.MapPost("/",
                async (CreateProjectDto project, ProjectsService service) => await service.Create(project))
            .AddEndpointFilter<ValidationFilter<CreateProjectDto>>()
            .WithSummary("Create project");

        projects.MapGet("/",
            async Task<Results<Ok<List<ProjectDto>>, ProblemHttpResult>> (ProjectsService service) =>
                TypedResults.Ok(await service.List())).WithSummary("Get projects");

        projects.MapGet("/{key}",
            async Task<Results<Ok<ProjectDto>, BadRequest<string>, NotFound>> (string key, ProjectsService service) =>
            {
                if (string.IsNullOrWhiteSpace(key))
                    return TypedResults.BadRequest("Invalid key");

                var project = await service.Get(key);

                return project is not null ? TypedResults.Ok(project) : TypedResults.NotFound();
            }).WithSummary("Get project");

        projects.MapDelete("/{key}",
            async Task<Results<Ok, BadRequest<string>, NotFound>> (string key, ProjectsService service) =>
            {
                if (string.IsNullOrWhiteSpace(key))
                    return TypedResults.BadRequest("Invalid key");

                var deleted = await service.Delete(key);

                return deleted ? TypedResults.Ok() : TypedResults.NotFound();
            }).WithSummary("Delete project");
    }
}