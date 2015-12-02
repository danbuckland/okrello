# Methods to create page objects from each screen class go here
class Screens < Base
	def main
		@main ||= page(MainScreen)
	end
end
