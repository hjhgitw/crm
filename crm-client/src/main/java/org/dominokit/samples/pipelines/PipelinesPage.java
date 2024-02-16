package org.dominokit.samples.pipelines;

import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.button.LinkButton;
import org.dominokit.domino.ui.icons.lib.Icons;
import org.dominokit.domino.ui.richtext.RichTextEditor;
import org.dominokit.domino.ui.style.Color;
import org.dominokit.domino.ui.utils.PostfixAddOn;
import org.dominokit.domino.ui.utils.PrefixAddOn;
import org.dominokit.samples.CrmPage;
import org.dominokit.samples.Opportunity;
import static org.dominokit.domino.ui.style.DominoCss.*;

public class PipelinesPage extends CrmPage<PipelinesPage> {

    private PipelineBoard board;
    private PipelineListGroup listGroup;

    public static PipelinesPage create() {
        return new PipelinesPage();
    }

    public PipelinesPage() {
        super("Pipelines");
        withPageNavBar((parent, pageNavBar) -> pageNavBar
                .appendChild(PrefixAddOn.of(Button.create(Icons.database_plus_outline(), "Create")
                                .addCss(dui_min_w_24)
                                .addClickListener(evt -> {
                                    CreateOpportunityDialog.create().open();
                                })
                        )
                )
                .appendChild(PrefixAddOn.of(LinkButton.create(Icons.database_import_outline(), "Import").addCss(dui_min_w_24)))
                .appendChild(PostfixAddOn.of(Icons.view_column()
                        .clickable()
                        .addClickListener(evt -> {
                            if (!board.isAttached()) {
                                listGroup.remove();
                                appendChild(board);
                            }
                        })
                ))
                .appendChild(PostfixAddOn.of(Icons.format_list_bulleted()
                        .clickable()
                        .addClickListener(evt -> {
                            if (!listGroup.isAttached()) {
                                board.remove();
                                appendChild(listGroup);
                            }
                        })
                ))
                .appendChild(PostfixAddOn.of(Icons.chart_bar().clickable()))
        );
        withContent((parent, content) -> content.addCss(dui_p_4, dui_flex, dui_items_stretch));
        listGroup = initListGroup();
        board = initBoard();
        appendChild(board)
        ;
    }

    private PipelineBoard initBoard() {
        return PipelineBoard.create()
                .appendChild(PipelineColumn.create("New leads", Color.AMBER)
                                   .appendChild(OpportunityCard.create(Opportunity.of("Opportunity L", "Customer L", 32000.0, 3)))
                        .appendChild(OpportunityCard.create(Opportunity.of("Opportunity M", "Customer M", 50000.0, 5)))
                )
                .appendChild(PipelineColumn.create("Qualified leads", Color.BLUE)
                            .appendChild(OpportunityCard.create(Opportunity.of("Opportunity R", "Customer R", 21000.0, 1)))
                )
                .appendChild(PipelineColumn.create("Negotiation", Color.INDIGO)
                        .appendChild(OpportunityCard.create(Opportunity.of("Opportunity V", "Customer V", 10500.0, 1)))
                        .appendChild(OpportunityCard.create(Opportunity.of("Opportunity W", "Customer W", 7000.0, 4)))
                )
                .appendChild(PipelineColumn.create("Won", Color.GREEN)
                        .appendChild(OpportunityCard.create(Opportunity.of("Opportunity Z", "Customer Z", 50000.0, 5)))
                )
              .appendChild(PipelineColumn.create("Extra column 4", Color.PURPLE));
    }

    private PipelineListGroup initListGroup() {
        return PipelineListGroup.create()
                .appendChild(PipelineList.create("New leads", Color.AMBER)
                        .addOpportunity(Opportunity.of("Opportunity A", "Customer A", 11000.0, 5))
                 )
                .appendChild(PipelineList.create("Qualified leads", Color.BLUE)
                         .addOpportunity(Opportunity.of("Opportunity R", "Customer R", 21000.0, 1))
                )
                .appendChild(PipelineList.create("Negotiation", Color.INDIGO)
                         .addOpportunity(Opportunity.of("Opportunity W", "Customer W", 7000.0, 4))
                )
                .appendChild(PipelineList.create("Won", Color.GREEN)
                         .addOpportunity(Opportunity.of("Opportunity Z", "Customer Z", 50000.0, 5))
                )
                 .appendChild(PipelineList.create("Extra list 4", Color.PURPLE));
    }
}
