package Main;

import Komponenter.Sko;

@FunctionalInterface
public interface ShoeSearcher {
    boolean search(Sko li, String searchWord);
}
