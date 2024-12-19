<%-- 
    Document   : borangN_adat
    Created on : Jul 8, 2011, 11:46:55 AM
    Author     : Akmal
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<script type="text/javascript">
    
    function validation() {
        var count = $("#count").val();
//        alert(count);
        for(var i=1;i<=count;i++){
            var tarikhBicara = $("#tarikhBicara"+(i - 1)).val();
            var jam = $("#jam"+(i - 1)).val();
            var minit = $("#minit"+(i - 1)).val();
            var ampm = $("#ampm"+(i - 1)).val();
            var lokasiBicara = $("#lokasiBicara"+(i - 1)).val();

            if(tarikhBicara == ""){
                alert('Sila pilih " Tarikh Perbicaraan " terlebih dahulu.');
                $("#tarikhBicara"+(i - 1)).focus();
                return false;
            }
            if(jam == ""){
                alert('Sila pilih " JAM " terlebih dahulu.');
                $("#jam"+(i - 1)).focus();
                return false;
            }
            if(minit == ""){
                alert('Sila pilih " MINIT " terlebih dahulu.');
                $("#minit"+(i - 1)).focus();
                return false;
            }
            if(ampm == ""){
                alert('Sila pilih " AM/PM " terlebih dahulu.');
                $("#ampm"+(i - 1)).focus();
                return false;
            }
            if(lokasiBicara == ""){
                alert('Sila pilih " Lokasi Perbicaraan " terlebih dahulu.');
                $("#lokasiBicara"+(i - 1)).focus();
                return false;
            }
        }
        return true;
    }
    
</script>
<s:form beanclass="etanah.view.stripes.pelupusan.BorangNAdatActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Surat Tuntut Bicara</legend><br/>
            <div class="content" align="center">
                <display:table class="tablecloth" cellpadding="0" cellspacing="0" name="${actionBean.senaraiBicara}" id="line" 
                               requestURI="${pageContext.request.contextPath}/pelupusan/borangN_adat">
                    <display:column title="Bil" sortable="true">${line_rowNum}
                    </display:column>
                    <display:column title="Nama">${line}</display:column>
                        <display:column title="Tarikh Perbicaraan">
                        <s:text name="tarikhBicara[${line_rowNum - 1}]"  class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" id="tarikhBicara${line_rowNum - 1}"/>
                        <s:hidden name="count" value="${fn:length(actionBean.senaraiBicara)}" id="count"/>
                    </display:column>
                   <display:column title="Waktu Perbicaraan" style="width:200px">
                       <s:select name="jam[${line_rowNum - 1}]" style="width:56px" id="jam${line_rowNum - 1}">
                        <s:option>Jam</s:option>
                        <s:option value="00">00</s:option>
                        <s:option value="01">01</s:option>
                        <s:option value="02">02</s:option>
                        <s:option value="03">03</s:option>
                        <s:option value="04">04</s:option>
                        <s:option value="05">05</s:option>
                        <s:option value="06">06</s:option>
                        <s:option value="07">07</s:option>
                        <s:option value="08">08</s:option>
                        <s:option value="09">09</s:option>
                        <s:option value="10">10</s:option>
                        <s:option value="11">11</s:option>
                        <s:option value="12">12</s:option>
                    </s:select>
                    <s:select name="minit[${line_rowNum - 1}]" style="width:60px" id="minit${line_rowNum - 1}">
                        <s:option>Minit</s:option>
                        <s:option value="00">00</s:option>
                        <s:option value="05">05</s:option>
                        <s:option value="10">10</s:option>
                        <s:option value="15">15</s:option>
                        <s:option value="20">20</s:option>
                        <s:option value="25">25</s:option>
                        <s:option value="30">30</s:option>
                        <s:option value="35">35</s:option>
                        <s:option value="40">40</s:option>
                        <s:option value="45">45</s:option>
                        <s:option value="50">50</s:option>
                        <s:option value="55">55</s:option>
                    </s:select>
                    <s:select name="ampm[${line_rowNum - 1}]" style="width:59px" id="ampm${line_rowNum - 1}">
                        <s:option value="">Pilih</s:option>
                        <s:option value="AM">AM</s:option>
                        <s:option value="PM">PM</s:option>
                    </s:select>
                   </display:column>
                    <display:column title="Lokasi Perbicaraan" style="width:80px"><s:text name="lokasiBicara[${line_rowNum - 1}]" id="lokasiBicara${line_rowNum - 1}"/></display:column>
                </display:table>
            </div>
            <p align="center">
               <s:button name="simpanSenaraiBicara" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div');"/>
            </p>

        </fieldset>
    </div>
</s:form>
