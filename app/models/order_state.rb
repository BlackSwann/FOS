class OrderState < ActiveRecord::Base

	has_many :orders

	def to_s
		name
	end
end
