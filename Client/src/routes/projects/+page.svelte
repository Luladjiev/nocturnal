<script lang="ts">
	import Link from '$lib/components/Link/Link.svelte';
	import { invalidate } from '$app/navigation';

	const { data } = $props();

	async function handleDelete(key: string) {
		const response = await fetch(`/api/projects/${key}`, { method: 'DELETE' });

		if (response.ok) {
			invalidate('/api/projects');
		}
	}
</script>

{#await data.projects then projects}
	{#each projects as project (project.key)}
		<div>
			<Link href="/projects/{project.key}">{project.name}</Link>
			-
			<button onclick={() => handleDelete(project.key)}>Del</button>
		</div>
	{/each}
{/await}
