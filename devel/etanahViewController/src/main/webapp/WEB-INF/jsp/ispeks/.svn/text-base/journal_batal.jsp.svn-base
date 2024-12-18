<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<html><head>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>
        <script src="<%= request.getContextPath()%>/js/ui.datepicker-ms.js" type="text/javascript"></script>
        <script type="text/javascript">

            function save(event, f){

                var q = $(f).formSerialize();
                var url = f.action + '?' + event;
                $.post(url,q,
                function(data){
                    $('#page_div',opener.document).html(data);
                    self.opener.refreshPage();
                    self.close();
                },'html');

            }
            function validateNumber(elmnt,content) {
                //if it is character, then remove it..
                if (isNaN(content)) {
                    elmnt.value = removeNonNumeric(content);
                    return;
                }
            }

            function removeNonNumeric( strString )
            {
                var strValidCharacters = "1234567890";
                var strReturn = "";
                var strBuffer = "";
                var intIndex = 0;
                // Loop through the string
                for( intIndex = 0; intIndex < strString.length; intIndex++ )
                {
                    strBuffer = strString.substr( intIndex, 1 );
                    // Is this a number
                    if( strValidCharacters.indexOf( strBuffer ) > -1 )
                    {
                        strReturn += strBuffer;
                    }
                }
                return strReturn;
            }
            function validateForm(){
                if($('#bpm').val() == ''){
                    alert("Sila Pilih Bandar/Pekan/Mukim");
                    $('#bpm').focus();
                    return false;
                }

                return true;
            }
            function test(f) {
                $(f).clearForm();
            }
            
            function textCounter(field, countfield, maxlimit) {
                if (field.value.length > maxlimit) // if too long...trim it!
                    field.value = field.value.substring(0, maxlimit);
                // otherwise, update 'characters left' counter
                else
                    countfield.value = maxlimit - field.value.length;
            }
     
        </script>
        
    </head><body>
        <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
        <s:form beanclass="etanah.view.stripes.ispeks.SediaJournalActionBean">
            <s:messages/>
            <div class="instr" align="center">
                <s:errors/>
            </div>
                <div class="subtitle">
            <fieldset class="aras1">
                <legend>Fail SPEKS </legend>

                <br />
                <p>
                    <label>Jenis Jurnal:</label>
                    ${actionBean.journalBatal.jenisJournal}
                </p>
                <p>
                    <label>No Baucer Jurnal :</label>
                    ${actionBean.journalBatal.noJournal}
                </p>
                <p>
                    <label>No Rujukan :</label>
                    ${actionBean.journalBatal.noRujukan}
                </p>
                <p>
                    <label>Tarikh Rujukan :</label>
                    ${actionBean.journalBatal.tarikhRujukan}
                </p>
<p>
                    <label>Perihal :</label>
                    <stripes:textarea name="perihal" readonly="true"/>

                </p>
                <br />
            
                <p>
                    <label>&nbsp;</label>
                    <stripes:submit name="batal" value="Batal" class="btn"/>
                    <stripes:submit name="simpan" value="Simpan" class="btn"/>
                    <stripes:button id="mt" name="downloadFile" value="Papar Penyata Journal" class="btn" onclick="papar('${actionBean.idJournal}')"/>
                    <stripes:submit name="selesai" value="Selesai" class="btn"/>

                </p>
                <br />
            </fieldset>
            <fieldset class="aras1">
                <legend>Fail SPEKS </legend>

                <p>
                    <display:table style="width:100%" class="tablecloth" name="${actionBean.senaraiJournal}"  cellpadding="0" cellspacing="0" id="line">
                        <display:column title="Bil">${line_rowNum}</display:column>
                        <display:column title="Vot Dana">
                            ${line.kodVotDana.id} | ${line.kodVotDana.perihal}
                        </display:column>
                        <display:column title="Akaun" > ${line.kodTransaksi.kod}|${line.kodTransaksi.nama}</display:column>
                        <display:column title="Debit" ><c:if test="${line.debit ne null}">RM <fmt:formatNumber value="${line.debit}" pattern="#,##0.00"/></c:if>&nbsp;</display:column>
                        <display:column title="Kredit"><c:if test="${line.kredit ne null}">RM <fmt:formatNumber value="${line.kredit}" pattern="#,##0.00"/></c:if>&nbsp;</display:column>
                        
                    </display:table>
                </p>
            </fieldset>
            <p>
                <label>&nbsp;</label>
                <stripes:hidden name="idJournal"/>
                <stripes:hidden name="idTugasan"/>
                <!-- stripes:button name="reset" value="Isi Semula" class="btn" onclick="click_clear();"/ -->
                <%--<stripes:submit id="mt" name="downloadFile" value="Muatturun" class="btn"/>--%>
            </p>
        </div>
        </s:form>
    </body></html>