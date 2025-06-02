using Microsoft.EntityFrameworkCore;
using nocturnal.Models;

namespace nocturnal.Db;

public class NocturnalDb(DbContextOptions options) : DbContext(options)
{
    public DbSet<Project> Projects { get; set; }
    public DbSet<Item> Items { get; set; }
    
    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder.Entity<Project>(entity =>
        {
            entity.HasKey(p => p.Key);
            entity.HasIndex(p => p.Key);
        });

        modelBuilder.Entity<Item>(entity =>
        {
            entity.HasKey(i => i.Key);
            entity.HasIndex(i => i.Key);
        });
    }

    public override Task<int> SaveChangesAsync(CancellationToken cancellationToken = default)
    {
        var entries = ChangeTracker.Entries<Base>();

        foreach (var entry in entries)
        {
            switch (entry.State)
            {
                case EntityState.Added:
                    entry.Entity.CreatedAt = DateTime.UtcNow;
                    entry.Entity.UpdatedAt = DateTime.UtcNow;
                    break;
                case EntityState.Modified:
                    entry.Entity.UpdatedAt = DateTime.UtcNow;
                    break;
            }
        }
        return base.SaveChangesAsync(cancellationToken);
    }
}