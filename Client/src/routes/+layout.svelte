<script lang="ts">
	import '../app.css';

	let { children } = $props();
</script>

<div>
	{@render children()}
</div>

<style>
	div {
		max-width: 900px;
		margin: 0 auto;
		padding: 20px;
	}
</style>
