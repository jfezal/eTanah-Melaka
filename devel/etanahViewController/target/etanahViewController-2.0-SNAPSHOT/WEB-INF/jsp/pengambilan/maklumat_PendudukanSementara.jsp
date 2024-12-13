<%-- 
    Document   : maklumat_PendudukanSementara
    Created on : 13-Dis-2010, 10:30:24
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
        if($("#datepicker").val() == ""){pen
            alert('Sila pilih " Tarikh Rujukan " terlebih dahulu.');
            $("#datepicker").focus();
            return true;
        }
        if($("#maksudpengambilan").val() == ""){
            alert('Sila masukkan " Maksud Pemulihan " terlebih dahulu.');
            $("#maksudpemulihan").focus();
            return true;
        }
        if($("#tanahasal").val() == ""){
            alert('Sila masukkan " Setelah Pengambilan Balik " terlebih dahulu.');
            $("#tanahasal").focus();
            return true;
        }
    }

       <%-- function doSubmit(event, f){
            alert(event +" " +f);
            if(validation()){

            }
            else{
                var q = $(f).formSerialize();
                var url = f.action + '?' + event;
                $.post(url,q,
                function(data){
                    $('#page_div',opener.document).html(data);
                    self.opener.refreshPageTanahRizab();
                    self.close();
                },'html');
            }
        }--%>
        </script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="listUtil" />

<s:form beanclass="etanah.view.stripes.pengambilan.MaklumatPendudukanSementara">
<s:messages/>
<s:errors/>
    <s:hidden name="permohonanRujukanLuar.idRujukan"/>
    <div class="subtitle">
    <fieldset class="aras1">
        <legend>
            Maklumat Pendudukan Sementara
        </legend>
        <c:if test="${edit}">
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq '31A'}">
                    <p>
                 <label for="status permohonan">Jenis Permohonan :</label>
                 <s:radio name="" value="Segera"/>&nbsp;Segera
                 <s:radio name="" value="Tidak Segera"/>&nbsp;Tidak Segera
                 </p>
                </c:if>
             
        <p>
            <label for="Maklumat Pengambilan">No Rujukan Surat :</label>
            <s:text name="permohonanRujukanLuar.noRujukan" size="30" id="norujukan" />
        </p>
       <%-- <p>
            <label for="Maklumat Pengambilan">Kod Rujukan :</label>
            <s:select name="kodRujukan.kod" style="width:400px" id="jabatan">
                                <s:option value="">Sila Pilih</s:option>
                                <s:option value="FL">Fail</s:option>
                                <s:option value="NF">No.Fail</s:option>
                            </s:select>
        </p>--%>
        <p>
            <label for="Trh_Surat_ Memohon">Tarikh Surat Memohon :</label>
            <s:text name="tarikhRujukan" id="datepicker" class="datepicker" />
        </p>

        <%--<p>
            <label for="Maksud_Pengambilan">Maksud Pengambilan :</label>
           <s:textarea name="permohonan.sebab" rows="4" cols="35" id="maksudpengambilan"/>
        </p>--%>

            <p>
                <label for="Maksud_Pengambilan">Maksud Penggunaan :</label>
               <s:textarea name="permohonan.sebab" rows="4" cols="35" id="maksudpengambilan" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
        
            <%--<p>
                 <label for="Tanah Asal">Setelah Pengambilan Tanah :</label>
                 <s:radio name="permohonanPengambilan.selepasPengambilan" value="Berimilik Semula"/>&nbsp;Hakmilik Sambungan
                 <s:radio name="permohonanPengambilan.selepasPengambilan" value="Rizab"/>&nbsp;Rizab
                 <s:radio name="permohonanPengambilan.selepasPengambilan" value="Tiada"/>&nbsp;Tanah Kerajaan
            </p>--%>
             <%--<p>
                <label>  Bandar/Pekan/Mukim :</label>
                <s:select name="bandarPekanMukim.kod" id="bpm"  >
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodBandarMukim}" label="nama" value="kod" sort="nama" />
                </s:select>
            </p>
            --%>
            <br>
            <p>
                <label>&nbsp;</label>
                <s:button name="savePengambilanInfo" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"  />
               <%-- onclick="save1(this.name, this.form);"--%>
            </p>
      </c:if>
      <c:if test="${!edit}">
          <p>
            <label for="Maklumat Pengambilan">No Rujukan Surat :</label>${actionBean.permohonanRujukanLuar.noRujukan}&nbsp;
          </p>
        <p>
            <label for="Trh_Surat_ Memohon">Tarikh Surat Memohon :</label>${actionBean.tarikhRujukan}&nbsp;
        </p>
        <p>
            <label for="Maksud_Pengambilan">Maksud Penggunaan :</label>${actionBean.permohonan.sebab}&nbsp;
           <%--<s:textarea name="permohonan.sebab" rows="4" cols="35" --%>
        </p>
<%--            <p>
                 <label for="Tanah Asal">Setelah Pengambilan Balik :</label>${actionBean.permohonanPengambilan.selepasPengambilan}&nbsp;
            </p>--%>
      </c:if>
    </fieldset>
</div>
    </s:form>
