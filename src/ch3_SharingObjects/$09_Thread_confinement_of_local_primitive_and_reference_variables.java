package ch3_SharingObjects;

import java.util.*;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/16 10:36 PM
 */
public class $09_Thread_confinement_of_local_primitive_and_reference_variables {

    public static class Animals {
        Ark ark;
        Species species;
        Gender gender;

        // 封闭栈，只将基本数据类型暴露给调用者
        public int loadTheArk(Collection<Animal> candidates) {
            SortedSet<Animal> animals;
            int numPairs = 0;
            Animal candidate = null;

            // animals confined to method, don't let them escape!
            animals = new TreeSet<>(new SpeciesGenderComparator());
            animals.addAll(candidates);
            for (Animal a : animals) {
                if (candidate == null || !candidate.isPotentialMate(a))
                    candidate = a;
                else {
                    ark.load(new AnimalPair(candidate, a));
                    ++numPairs;
                    candidate = null;
                }
            }
            return numPairs;
        }

        class Animal {
            Species species;
            Gender gender;

            public boolean isPotentialMate(Animal other) {
                return species == other.species && gender != other.gender;
            }
        }

        enum Species {
            AARDVARK, BENGAL_TIGER, CARIBOU, DINGO, ELEPHANT, FROG, GNU, HYENA,
            IGUANA, JAGUAR, KIWI, LEOPARD, MASTADON, NEWT, OCTOPUS,
            PIRANHA, QUETZAL, RHINOCEROS, SALAMANDER, THREE_TOED_SLOTH,
            UNICORN, VIPER, WEREWOLF, XANTHUS_HUMMINBIRD, YAK, ZEBRA
        }

        enum Gender {
            MALE, FEMALE
        }

        class AnimalPair {
            private final Animal one, two;

            public AnimalPair(Animal one, Animal two) {
                this.one = one;
                this.two = two;
            }
        }

        class SpeciesGenderComparator implements Comparator<Animal> {
            public int compare(Animal one, Animal two) {
                int speciesCompare = one.species.compareTo(two.species);
                return (speciesCompare != 0)
                        ? speciesCompare
                        : one.gender.compareTo(two.gender);
            }
        }

        class Ark {
            private final Set<AnimalPair> loadedAnimals = new HashSet<AnimalPair>();

            public void load(AnimalPair pair) {
                loadedAnimals.add(pair);
            }
        }
    }
}
