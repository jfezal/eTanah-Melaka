<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<script type="text/javascript">


    function removeSingle (idMesej) {
        form = document.form1;
        var answer = confirm("adakah anda pasti untuk Hapus?");
        if (answer) {
            form.action = "${pageContext.request.contextPath}/mesej/{idMesej}?deleteSingle&idNotifikasi="+idMesej;
            form.submit();
        }
    }

   function resetText(){

   $('#tajuk').val('');
   $('#penghantar').val('');
   $('#datepicker').val('');
   $('#datepicker2').val('');

   return true;

   }
   

</script>
<stripes:messages />
<stripes:errors />
<br>

<stripes:form id="mesej_pguna" beanclass="etanah.view.notifikasi.NotifikasiAction" name="form1">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Carian
            </legend>
            <div class="content" align="center">


                <table>
                    <tr>
                        <td class="rowlabel1">Tajuk :</td>
                        <td class="input1"><s:text name="tajuk" id="tajuk" size="70"/> </td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">&nbsp;</td>
                        <td ><font color="red">ATAU</font></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Penghantar :</td>
                        <td class="input1"><s:text name="penghantar" id="penghantar" size="70"/> </td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">&nbsp;</td>
                        <td ><font color="red">ATAU</font></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Tarikh Dari :</td>
                        <td class="input1">
                            <s:text name="fromDate" id="datepicker" class="datepicker"
                                    onblur="editDateBlur(this, 'DD/MM/YYYY')"
                                    onkeypress="return editDatePre(this, 'DD/MM/YYYY', event)"
                                    onkeyup="return editDate(this, 'DD/MM/YYYY', event);"/> <font size="1" color="red">[hh/bb/tttt]</font>
                        </td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Tarikh Hingga :</td>
                        <td class="input1">
                            <s:text name="untilDate" id="datepicker2" class="datepicker"
                                    onblur="editDateBlur(this, 'DD/MM/YYYY')"
                                    onkeypress="return editDatePre(this, 'DD/MM/YYYY', event)"
                                    onkeyup="return editDate(this, 'DD/MM/YYYY', event);"/> <font size="1" color="red">[hh/bb/tttt]</font>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <s:submit name="searchAllMesej" value="Cari" class="btn"/>

                            <s:button  name="reset" value="Isi Semula" class="btn" onclick="resetText('mesej_pguna');"/>
                        </td>
                    </tr>
                </table>

                <br>

            </div>
        </fieldset>
    </div>
    <br>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Senarai Mesej
            </legend>


    <p class=instr>Senarai mesej untuk anda.</p>
    <fieldset class="aras1">

        <display:table name="${actionBean.listNotifikasi}" id="row" class="tablecloth" style="width:100%;">
            <display:column title="Tarikh" property="infoAudit.tarikhMasuk" format="{0, date, dd/MM/yyyy hh:mm aa}"/>
            <display:column title="Daripada" property="infoAudit.dimasukOleh.nama"/>
            <display:column title="Tajuk" property="tajuk"/>
           <display:column title="Keterangan" property="mesej" maxLength="1000" />
            <display:column title="Hapus">
                <div align="center">
                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                         id='rem_${row_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSingle('${actionBean.listNotifikasi[row_rowNum-1].idNotifikasi}');" />
                </div>
            </display:column>
        </display:table>
     </fieldset>
    </fieldset>
    </div>
</stripes:form>
