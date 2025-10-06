<script lang="ts">
	import { page } from '$app/state';
	import ErrorView from '$lib/components/ErrorView/ErrorView.svelte';
</script>

<ErrorView status={page.status}>
	{page.error?.message}
</ErrorView>
