<script lang="ts">
	import Link from '$lib/components/Link/Link.svelte';
	import { invalidate } from '$app/navigation';
	import { Check, SquarePlus, Trash2, X } from '@lucide/svelte';
	import ButtonIcon from '$lib/components/ButtonIcon/ButtonIcon.svelte';

	const { data } = $props();

	let confirmDeleteKey = $state('');

	async function handleDelete(key: string) {
		const response = await fetch(`/api/projects/${key}`, { method: 'DELETE' });

		if (response.ok) {
			invalidate('/api/projects');
		}
	}

	function cancelDeletion() {
		confirmDeleteKey = '';
	}

	function handleNew() {
		location.assign('/projects/new');
	}
</script>

{#await data.projects then projects}
	<div class="table-wrap">
		<table class="table table-fixed">
			<thead>
				<tr>
					<th class="w-3xs">Name</th>
					<th>Description</th>
					<th class="w-24">
						<ButtonIcon Icon={SquarePlus} variant="green" onclick={handleNew}></ButtonIcon>
					</th>
				</tr>
			</thead>
			<tbody>
				{#each projects as project (project.key)}
					<tr>
						<td>
							<Link href="/projects/{project.key}">{project.name} ({project.key})</Link>
						</td>
						<td class="truncate" title={project.description}>
							{project.description}
						</td>
						<td class="flex gap-1">
							{#if confirmDeleteKey === project.key}
								<ButtonIcon Icon={X} variant="red" onclick={cancelDeletion} />
								<ButtonIcon
									Icon={Check}
									variant="green"
									onclick={() => handleDelete(project.key)}
								/>
							{:else}
								<ButtonIcon
									Icon={Trash2}
									variant="red"
									onclick={() => (confirmDeleteKey = project.key)}
								/>
							{/if}
						</td>
					</tr>
				{/each}
			</tbody>
		</table>
	</div>
{/await}
