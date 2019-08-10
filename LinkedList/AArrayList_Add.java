try {
		if(size==elements.length) {
			ensureCapacity();
			}
			/*
			 * elements[I]= e; size++;
			 */
		
	for (int k=size-1; k>=I; k--) { // start by shifting rightmost
			//System.out.println("size"+k);
			elements[k+1] = elements[k];
			elements[I] = e; // ready to place the new element
			size++;
		System.out.println("successfully inserted value");
		}
			}
	catch (ArrayIndexOutOfBoundsException m) {
				checkIndex(I,size+1);
				//System.out.println("Invalid index to access array");
			}
		}
	