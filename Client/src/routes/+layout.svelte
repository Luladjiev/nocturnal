<script lang="ts">
	import '../app.css';

	let { children } = $props();
</script>

<div class="m-auto w-4xl p-4">
	{@render children()}
</div>
