<%-- 
    Document   : rekod_keputusan_mmkNS
    Created on : Apr 24, 2011, 7:06:26 PM
    Author     : Murali
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<script type="text/javascript">

    function ReplaceAll(Source,stringToFind,stringToReplace){
        var temp = Source;
        var index = temp.indexOf(stringToFind);
        while(index != -1){
            temp = temp.replace(stringToFind,stringToReplace);
            index = temp.indexOf(stringToFind);

        }
        return temp;
    }
    function RunProgram(strUserID, strNama, strJawatan, strIDPermohonan, strIDStage) {

       alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strIDStage);
       strNama = ReplaceAll(strNama," ","_");
       strJawatan = ReplaceAll(strJawatan," ","_");
       var stageId = "g_charting_keputusan";
       alert("nama:" + strNama);
       alert ("jawatan:" + strJawatan);
       alert ("stageid:" + stageId);
       var objShell = new ActiveXObject("WScript.Shell");
       var sysVar = objShell.Environment("System");
       objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + stageId);
   }



</script>
<s:form beanclass="etanah.view.stripes.pelupusan.KeputusanMMKActionBean">
    <s:messages/>
    <s:errors/>

    <div class="subtitle">
        <fieldset class="aras1">
            <div class="content">

                <p>
                <label>Kertas Mesyuarat No :</label>
                <c:if test="${!edit}">
                <s:text name="permohonanKertas.nomborRujukan" size="30" id="nomborRujukan"/>
                </c:if>

                <c:if test="${edit}">
                ${actionBean.permohonanKertas.nomborRujukan}
                </c:if>

               </p>

               <p></p>
               
               <p>
                <label>Tarikh Mesyuarat :</label>
                <c:if test="${!edit}">
                <s:text name="permohonanKertas.tarikhSidang" size="30" id="tarikhSidang" class="datepicker" formatPattern="dd/MM/yyyy"/>
                </c:if>

                <c:if test="${edit}">
                ${actionBean.permohonanKertas.tarikhSidang}
                </c:if>

               </p>

              <p></p>

              <p>
                <label>Keputusan Mesyuarat :</label>
                <c:if test="${!edit}">
                <s:select name="permohonan.keputusan.kod" id="keputusan" value="${actionBean.permohonan.keputusan.kod}"><%--${actionBean.permohonan.keputusan.nama}--%>
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:option value="T"> Tolak </s:option>
                                <s:option value="L"> Lulus </s:option>
                                <s:option value="TQ"> Tangguh </s:option>
                            </s:select>
                </c:if>

                 <c:if test="${edit}">
                ${actionBean.permohonan.keputusan.nama}
                </c:if>

              </p>

              <p></p>

              <p>
                <label>Catatan :</label>
                <c:if test="${!edit}">
                <s:textarea rows="7" cols="60" name="permohonan.ulasan" id="ulasan" value="" ></s:textarea>
                </c:if>

                <c:if test="${edit}">
                ${actionBean.permohonan.ulasan}
                </c:if>

                </p><br>

              <p></p>

              <p align="center">
                  <c:if test="${!edit}">
              <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                  </c:if>

                  <c:if test="${edit}">
              <s:hidden name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                  </c:if>
              
              <c:if test="${edit1}">
                  <s:button class="btn" value="Charting" name="charting" id="charting" onclick="RunProgram('${actionBean.peng.idPengguna}','${actionBean.peng.nama}','${actionBean.peng.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');" />
                </c:if>

              </p>

            </div>
        </fieldset>
    </div>
    </s:form>
