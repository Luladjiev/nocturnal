using Microsoft.AspNetCore.Http.HttpResults;
using nocturnal.DTO;
using nocturnal.Filters;
using nocturnal.Services;

namespace nocturnal.Routes;

public static class Projects
{
    public static void MapProjectsRoutes(this RouteGroupBuilder group)
    {
        group.MapPost("/projects", (CreateProjectDto project, ProjectsService service) => service.Create(project))
            .AddEndpointFilter<ValidationFilter<CreateProjectDto>>()
            .WithSummary("Create project");

        group.MapGet("/projects",
                async Task<Results<Ok<List<ProjectDto>>, ProblemHttpResult>> (ProjectsService service) =>
                    TypedResults.Ok(await service.List()))
            .WithSummary("Get projects");

        group.MapGet("/projects/{key}",
                async Task<Results<Ok<ProjectDto>, BadRequest, NotFound>> (string key, ProjectsService service) =>
                {
                    if (string.IsNullOrWhiteSpace(key))
                        return TypedResults.BadRequest();

                    var project = await service.Get(key);

                    return project is not null ? TypedResults.Ok(project) : TypedResults.NotFound();
                })
            .WithSummary("Get project");
    }
}