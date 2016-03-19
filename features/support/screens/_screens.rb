##
# Methods to create page objects from each screen class go here.
##

class Screens < Calabash::ABase
	def main
		@main ||= page(MainScreen)
	end

	def objectives
		@objectives ||= page(ObjectivesScreen)
	end

	def key_results
		@key_results ||= page(KeyResultsScreen)
	end
end
