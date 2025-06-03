<script lang="ts">
	export let href;
</script>

<a {href}>
	<slot />
</a>

<style>
	a {
		color: #1ea7fd;

		&:hover {
			text-decoration: underline;
		}

		&:visited {
			color: #555ab9;
		}
	}
</style>
