
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.7.2.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.tabs.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.datetimepicker.js"></script>
<script type="text/javascript">

    function resetLuas(){
        document.getElementById("luasBaru").value = '';
        return true;
    }

    function validate(){
        var luasAsal = ${actionBean.hakmilik.luas};
        var luasBaru = document.getElementById("luasBaru").value;

        if (luasBaru > luasAsal){
            alert("Sila masukkan luas baru yang lebih kecil dari luas lama");
            $('#luasBaru').focus();
            return false;
        }

        return true;
    }

    function ReplaceAll(Source,stringToFind,stringToReplace){
        var temp = Source;
        var index = temp.indexOf(stringToFind);
        while(index != -1){
            temp = temp.replace(stringToFind,stringToReplace);
            index = temp.indexOf(stringToFind);

        }
    <%--alert(temp);--%>
            return temp;
        }

        function RunProgram(strUserID, strNama, strJawatan, strIDPermohonan, strStageID) {
    <%--var stageId = "g_charting_keputusan";--%>
            // replace " " with "_"

            strNama = ReplaceAll(strNama," ","_");
            strJawatan = ReplaceAll(strJawatan," ","_");
            strStageID = ReplaceAll(strStageID," ","_");
            alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strStageID);
    <%--
    alert ("stageid:" + stageId);--%>
            var objShell = new ActiveXObject("WScript.Shell");
            var sysVar = objShell.Environment("System");
            //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
            //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")


            objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + strStageID);
        }

</script>

<s:form beanclass="etanah.view.penguatkuasaan.MaklumatKeputusanActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklum Keputusan
            </legend>
            <div class="content" >
                <c:if test="${actionBean.recordCount == 0}">
                    Tiada Data
                </c:if>
                <c:if test="${actionBean.recordCount > 1}">
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="line">
                        <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}</display:column>
                        <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" style="vertical-align:baseline"/>
                        <display:column property="hakmilik.cawangan.name" title="Cawangan" style="vertical-align:baseline"/>
                        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah" style="vertical-align:baseline"/>
                        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" style="vertical-align:baseline"/>
                        <display:column property="hakmilik.lot.nama" title="Unit Lot" style="vertical-align:baseline"/>
                        <display:column property="hakmilik.noLot" title="No Lot" style="vertical-align:baseline"/>
                        <display:column property="hakmilik.kategoriTanah.nama" title="Kategori Tanah" style="vertical-align:baseline"/>
                        <display:column title="Luas Lama" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                        <display:column title="Luas Baru" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${line.luasTerlibat}"/>&nbsp;${line.kodUnitLuas.nama}</display:column>
                    </display:table>

                </c:if>
                <c:if test="${actionBean.recordCount == 1}">
                    <p>
                        <label>ID Hakmilik :</label>&nbsp;
                        ${actionBean.hakmilik.idHakmilik}&nbsp;
                        <s:hidden name="hakmilik.idHakmilik" id="idHakmilik"/>
                    </p>
                    <p>
                        <label>Cawangan :</label>&nbsp;
                        ${actionBean.hakmilik.cawangan.name}&nbsp;
                    </p>
                    <p>
                        <label>Daerah :</label>&nbsp;
                        ${actionBean.hakmilik.daerah.nama}&nbsp;
                    </p>
                    <p>
                        <label>Bandar/Pekan/Mukim :</label>&nbsp;
                        ${actionBean.hakmilik.bandarPekanMukim.nama}&nbsp;
                    </p>
                    <p>
                        <label>Unit Lot :</label>&nbsp;
                        ${actionBean.hakmilik.lot.nama}&nbsp;
                    </p>
                    <p>
                        <label>No Lot :</label>&nbsp;
                        ${actionBean.hakmilik.noLot}&nbsp;
                    </p>
                    <p>
                        <label>Kategori Tanah :</label>&nbsp;
                        ${actionBean.hakmilik.kategoriTanah.nama}&nbsp;
                    </p>
                    <p>
                        <label>Luas Lama :</label>&nbsp;
                        ${actionBean.hakmilik.luas}&nbsp;${actionBean.hakmilik.kodUnitLuas.nama}
                    </p>
                    <p>
                        <label>Luas Baru :</label>&nbsp;
                        <s:text name="hakmilikPermohonan.luasTerlibat" id="luasBaru" size = "10"/>&nbsp;
                        <s:select name="kodUnitLuas.kod" id="uom" value="${actionBean.hakmilikPermohonan.kodUnitLuas.kod}">
                            <s:option value="0">Sila Pilih</s:option>
                            <s:option value="M">METER PERSEGI</s:option>
                            <s:option value="H">HEKTAR</s:option>
                        </s:select>
                    </p>
                </c:if>

            </div>
        </fieldset>
    </div>
    <p align="center">

        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validate())doSubmit(this.form, this.name,'page_div')"/>
        <c:if test="${actionBean.stageId ne 'g_laporan_tanah_l'}">
            <s:button name="maklumKeputusanGIS" id="maklumKeputusanGIS" value="Charting Keputusan" class="longbtn"  onclick="RunProgram('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
        </c:if>




    </p>
</s:form>
