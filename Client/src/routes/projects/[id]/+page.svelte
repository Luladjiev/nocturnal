<script lang="ts">
	const { data } = $props();
</script>

{#await data.project}
	Loading...
{:then project}
	<h1>{project.name}</h1>
	<p>{project.description}</p>
{/await}
