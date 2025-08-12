/* CodingNomads (C)2024 */
package com.codingnomads.corespring.examples.primaryannotation;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DesktopComputer {

    @Autowired
    private VideoCard videoCard;

    @Getter
    @Autowired
    private Processor processor;
}
