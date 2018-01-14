package service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Animal;
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

}
