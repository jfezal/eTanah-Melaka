<%--
    Document   : permohonanLaluLalang
    Created on : 05-Jan-2011, 11:54:11
    Author     : massita
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>

<script type="text/javascript">

function validation() {
        if($("#norujukan").val() == ""){
            alert('Sila masukkan " No Rujukan " terlebih dahulu.');
            $("#norujukan").focus();
            return false;
        }
        if($("#datepicker").val() == ""){
            alert('Sila pilih " Tarikh Rujukan " terlebih dahulu.');
            $("#datepicker").focus();
            return true;
        }
        if($("#maksudpengambilan").val() == ""){
            alert('Sila masukkan " Maksud Pengambilan " terlebih dahulu.');
            $("#maksudpengambilan").focus();
            return true;
        }
        if($("#tanahasal").val() == ""){
            alert('Sila membuat pilihan ruangan " Setelah Pengambilan Balik " terlebih dahulu.');
            $("#tanahasal").focus();
            return true;
        }
    }
        </script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="listUtil" />

<s:form beanclass="etanah.view.stripes.pengambilan.PermohonanLaluLalangActionBean">
<s:messages/>
<s:errors/>
    <%--<s:hidden name="permohonanRujukanLuar.idRujukan"/>--%>
    <div class="subtitle">
    <fieldset class="aras1">
        <br />
        <legend>
            Maklumat Permohonan
       </legend>
        <%--<c:if test="${edit}">--%>
        <table>
            <tr><td>
                <label>Tarikh Permohonan :</label>
                <s:text name="s" id="datepicker" class="datepicker" />
            </td></tr>
            <tr><td>
                 <label>Jenis Permohonan Hak Lalu Lalang :</label>
                 <s:checkbox name="" value="Persendirian"/>&nbsp;Persendirian
                 <s:checkbox  name="" value="Awam"/>&nbsp;Awam
            </td></tr>
            <tr><td>
                <label>Stesyen Laluan Awam Terdekat :</label>
               <s:text name="c"  id="maksudpengambilan"/>
            </td></tr>
            <br /><br />
            <tr><td>
                <label>Mukim :</label>
                <s:select name="bandarPekanMukim.kod" id="bpm"  >
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodBandarMukim}" label="nama" value="kod" sort="nama" />
                </s:select>
           </td></tr>
            <tr><td>
                <label>  Daerah :</label>
                <s:select name="daerah.kod" id="daerah">
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod" sort="nama"  />
                </s:select>
            </td></tr>
            <table align="center">
            <td align="center">
                <s:button name="savePengambilanInfo" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"  />
            </td>
            </table>
        </table><br />
      <%--</c:if>--%>
      <%--<c:if test="${!edit}">
          <p>
            <label for="Maklumat Pengambilan">No Rujukan Surat :</label>${actionBean.permohonanRujukanLuar.noRujukan}&nbsp;
          </p>
        <p>
            <label for="Trh_Surat_ Memohon">Tarikh Surat Memohon :</label>${actionBean.permohonanRujukanLuar.tarikhRujukan}&nbsp;
        </p>

        <p>
            <label for="Maksud_Pengambilan">Maksud Pengambilan :</label>${actionBean.permohonan.sebab}&nbsp;
           <s:textarea name="permohonan.sebab" rows="4" cols="35" 
        </p>
            <p>
                 <label for="Tanah Asal">Setelah Pengambilan Tanah :</label>${actionBean.permohonanPengambilan.selepasPengambilan}&nbsp;
            </p>
      </c:if>
    --%></fieldset>
</div>

    </s:form>
