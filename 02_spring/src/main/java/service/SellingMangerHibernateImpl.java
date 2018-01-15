package service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Animal;
import domain.Address;
import domain.Breeder;
import domain.Zoo;

@Component
@Transactional
public class SellingMangerHibernateImpl implements SellingManagerInterface {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addZoo(Zoo zoo) {
		zoo.setId(null);
		sessionFactory.getCurrentSession().persist(zoo);
	}

  @Override
  public void updateZoo(Zoo oldZoo, Zoo newZoo) {
    oldZoo = (Zoo) sessionFactory.getCurrentSession().get(Zoo.class, oldZoo.getId());
    oldZoo.setName(newZoo.getName());
    oldZoo.setOwner(newZoo.getOwner());
    oldZoo.setAddress(newZoo.getAddress());
    sessionFactory.getCurrentSession().update(oldZoo);
  }

	@Override
	public void deleteZoo(Zoo zoo) {
		zoo = (Zoo) sessionFactory.getCurrentSession().get(Zoo.class, zoo.getId());

		// lazy loading here
		for (Animal animal : zoo.getAnimals()) {
			animal.setSold(false);
			sessionFactory.getCurrentSession().update(animal);
		}
		sessionFactory.getCurrentSession().delete(zoo);
	}

	@Override
	public List<Animal> getOwnedAnimals(Zoo zoo) {
		zoo = (Zoo) sessionFactory.getCurrentSession().get(Zoo.class, zoo.getId());

		// lazy loading here - try this code without (shallow) copying
		List<Animal> animals = new ArrayList<Animal>(zoo.getAnimals());
		return animals;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Zoo> getAllZoos() {
		return sessionFactory.getCurrentSession().getNamedQuery("zoo.all").list();
	}

	@Override
	public Zoo findZooByOwner(String owner) {
		return (Zoo) sessionFactory.getCurrentSession().getNamedQuery("zoo.byOwner").setString("owner", owner).uniqueResult();
	}


	@Override
	public Long addNewAnimal(Animal animal) {
		animal.setId(null);
		return (Long) sessionFactory.getCurrentSession().save(animal);
	}

  @Override
  public void updateAnimal(Animal oldAnimal, Animal newAnimal) {
    oldAnimal = (Animal) sessionFactory.getCurrentSession().get(Animal.class, oldAnimal.getId());
    oldAnimal.setSpecies(newAnimal.getSpecies());
    oldAnimal.setName(newAnimal.getName());
    oldAnimal.setSold(newAnimal.getSold());
    oldAnimal.setBreeder(newAnimal.getBreeder());
    sessionFactory.getCurrentSession().update(oldAnimal);
  }

	@Override
	public void sellAnimal(Long zooId, Long animalId) {
		Zoo zoo = (Zoo) sessionFactory.getCurrentSession().get(Zoo.class, zooId);
		Animal animal = (Animal) sessionFactory.getCurrentSession().get(Animal.class, animalId);
		animal.setSold(true);
		zoo.getAnimals().add(animal);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Animal> getAvailableAnimals() {
		return sessionFactory.getCurrentSession().getNamedQuery("animal.unsold").list();
	}

  @Override
  @SuppressWarnings("unchecked")
  public List<Animal> getAllAnimals() {
    return sessionFactory.getCurrentSession().getNamedQuery("animal.all").list();
  }

  @Override
  public void deleteAnimal(Animal animal) {

    animal = (Animal) sessionFactory.getCurrentSession().get(Animal.class, animal.getId());

    List<Zoo> zoos = getAllZoos();
    for (Zoo aZoo : zoos) {
      for (Animal anAnimal : aZoo.getAnimals()) {
        if (anAnimal.getId().compareTo(animal.getId()) == 0) {
          disposeAnimal(aZoo, animal);
          break;
        }
      }
    }

    sessionFactory.getCurrentSession().delete(animal);
  }

	@Override
	public void disposeAnimal(Zoo zoo, Animal animal) {

		zoo = (Zoo) sessionFactory.getCurrentSession().get(Zoo.class, zoo.getId());
		animal = (Animal) sessionFactory.getCurrentSession().get(Animal.class, animal.getId());

		Animal toRemove = null;
		// lazy loading here (zoo.getAnimals)
		for (Animal anAnimal : zoo.getAnimals())
			if (anAnimal.getId().compareTo(animal.getId()) == 0) {
				toRemove = anAnimal;
				break;
			}

		if (toRemove != null)
			zoo.getAnimals().remove(toRemove);

		animal.setSold(false);
	}

	@Override
	public Animal findAnimalById(Long id) {
		return (Animal) sessionFactory.getCurrentSession().get(Animal.class, id);
	}

  @Override
  public void addBreeder(Breeder breeder) {
    breeder.setId(null);
    sessionFactory.getCurrentSession().persist(breeder);
  }

  @Override
	@SuppressWarnings("unchecked")
  public List<Breeder> getAllBreeders() {
		return sessionFactory.getCurrentSession().getNamedQuery("breeder.all").list();
  }

  @Override
  public void updateBreeder(Breeder oldBreeder, Breeder newBreeder) {
    oldBreeder = (Breeder) sessionFactory.getCurrentSession().get(Breeder.class, oldBreeder.getId());
    oldBreeder.setFirstName(newBreeder.getFirstName());
    oldBreeder.setBreedingSpecies(newBreeder.getBreedingSpecies());
    oldBreeder.setAddress(newBreeder.getAddress());
    sessionFactory.getCurrentSession().update(oldBreeder);
  }

  @Override
	public void deleteBreeder(Breeder breeder) {

		breeder = (Breeder) sessionFactory.getCurrentSession().get(Breeder.class, breeder.getId());

    List<Animal> animals = sessionFactory.getCurrentSession().getNamedQuery("animal.all").list();
		for (Animal anAnimal : animals) {
			if (anAnimal.getBreeder().getId().compareTo(breeder.getId()) == 0) {
        anAnimal.setBreeder(null);
        sessionFactory.getCurrentSession().update(anAnimal);
			}
    }
    sessionFactory.getCurrentSession().delete(breeder);
	}

  @Override
  public Breeder findBreederById(Long id) {
    return (Breeder) sessionFactory.getCurrentSession().get(Breeder.class, id);
  }

  @Override
  public Breeder findBreederByBreedingSpecies(String breedingSpecies) {
    return (Breeder) sessionFactory.getCurrentSession().getNamedQuery("breeder.bySpecies").setString("breedingSpecies", breedingSpecies).uniqueResult();
  }

  @Override
  public Animal findAnimalByBreeder(Breeder breeder) {
    return (Animal) sessionFactory.getCurrentSession().getNamedQuery("animal.byBreeder").setLong("breeder", breeder.getId()).uniqueResult();
  }

  @Override
  public void addAddress(Address address) {
    address.setId(null);
    sessionFactory.getCurrentSession().persist(address);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Address> getAllAddresses() {
    return sessionFactory.getCurrentSession().getNamedQuery("address.all").list();
  }

  @Override
  public void updateAddress(Address oldAddress, Address newAddress) {
    oldAddress = (Address) sessionFactory.getCurrentSession().get(Address.class, oldAddress.getId());
    oldAddress.setStreet(newAddress.getStreet());
    oldAddress.setNumber(newAddress.getNumber());
    oldAddress.setPostalCode(newAddress.getPostalCode());
    oldAddress.setCity(newAddress.getCity());
    oldAddress.setCountry(newAddress.getCountry());
    sessionFactory.getCurrentSession().update(oldAddress);
  }

  @Override
  public void deleteAddress(Address address) {

		address = (Address) sessionFactory.getCurrentSession().get(Address.class, address.getId());

    List<Breeder> breeders = sessionFactory.getCurrentSession().getNamedQuery("breeder.all").list();
    List<Zoo> zoos = sessionFactory.getCurrentSession().getNamedQuery("zoo.all").list();

		for (Breeder aBreeder : breeders) {
			if (aBreeder.getAddress().getId().compareTo(address.getId()) == 0) {
        aBreeder.setAddress(null);
        sessionFactory.getCurrentSession().update(aBreeder);
			}
    }

    for (Zoo aZoo : zoos) {
			if (aZoo.getAddress().getId().compareTo(address.getId()) == 0) {
        aZoo.setAddress(null);
        sessionFactory.getCurrentSession().update(aZoo);
			}
    }

    sessionFactory.getCurrentSession().delete(address);
  }

}
