<%-- 
    Document   : select_senarai_hakmilik
    Created on : Dec 24, 2009, 1:10:53 PM
    Author     : mohd.fairul
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript">
    $(document).ready( function() {


        var len = $(".daerah").length;

        for (var i=0; i<=len; i++){
            $('.hakmilik'+i).click( function() {
                window.open("${pageContext.request.contextPath}/hakmilik?hakmilikDetail&idHakmilik="+$(this).text(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
            });
        }
    });

    function clickEdit()
    {
    
    }
</script>

<s:form beanclass="etanah.view.stripes.common.MaklumatHakmilikPermohonanActionBean">
    <div class="subtitle displaytag">
    <fieldset class="aras1">
            <legend>
                Perihal Pembetulan
            </legend>
            <p style="color:red">
                *Sila masukan Perihal Pembetulan dan klik butang Tambah<br/>
            </p>
            <div class="content" align="center">
                <%--<display:table class="tablecloth" name="" pagesize="10" cellpadding="0" cellspacing="0" id="line">
                    <display:column title="Pilih" sortable="true" ></display:column>
                    <display:column title="Pengenalan" />
                    <display:column title="Nama Pemberi"/>
                    <display:column title="padam">test
                    </display:column>
                </display:table>--%>
                <s:textarea name="perihalPembetulan" cols="40"></s:textarea>
                <table style="margin-left: auto; margin-right: auto;">
                    <tr>
                        <td>&nbsp;</td>
                        <td><div >

                                <s:button class="btn" onclick="doSubmit(this.form, this.name, 'page_div');" name="savePengambilan" value="Tambah"/>


                            </div>
                        </td>
                    </tr>
                </table>
                <table class="tablecloth">
                    <tr><th>Bil.</th><th>Perihal Pembetulan</th><th>Hapus</th></tr>
                    <tr><td>1</td><td >Baiki Hakmilik Sebelumkini</td><td><s:button name="hapus" value="Hapus" /></td></tr>
                </table>
            </div>

        </fieldset>
        <fieldset class="aras1">
            <legend>
                Senarai Hakmilik Terlibat
            </legend>
            <p style="color:red">
                *Sila pilih Hakmilik untuk membuat pembetulan.<br/>
                *Klik pada Id Hakmilik untuk melihat maklumat hakmilik terperinci.
            </p>
            <p align="center"><label></label>
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}"  cellpadding="0" cellspacing="0"
                               requestURI="/common/maklumat_hakmilik_permohonan" id="line">
                    <display:column title="Bil">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}"/>
                    <%--<display:column property="hakmilik.noLot" title="No Lot" />--%>
                    <%--<display:column property="hakmilik.luas" title="Luas" />--%>
                    <%--<display:column title="Luas"><fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>--%>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                    <%--<display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />--%>
                    <display:column property="hakmilik.tarikhDaftar" title="Tarikh Daftar" />
                    <display:column title="Pilih"><s:radio name="pilih" value="${line_rowNum}"></s:radio></display:column>
                    </display:table>


                &nbsp;
            </p>
            <%--<table style="margin-left: auto; margin-right: auto;">
                    <tr>
                        <td>&nbsp;</td>
                        <td><div >

                                <s:button class="btn" onclick="doSubmit(this.form, this.name, 'page_div');" name="savePengambilan" value="Simpan"/>


                            </div>
                        </td>
                    </tr>
                </table>--%>
        </fieldset>
    </div>
    <div id="list_pb"/>
</s:form>
