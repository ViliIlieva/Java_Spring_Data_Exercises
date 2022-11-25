package softuni.exam.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class TownImportDTO {

    @Size(min = 2)
    private String townName;

    @Positive
    private int population;
}
