import type { PageLoad } from './$types';
import { error } from '@sveltejs/kit';

export const load: PageLoad = async ({ fetch, params }) => {

	return {
		project: fetch(`/api/projects/${params.id}`).then(response => {
			if (!response.ok) {
				if (response.status === 404) {
					error(404, 'Project not found');
				}

				error(response.status, 'Failed to load project');
			}

			return response.json();
		})
	};
};
