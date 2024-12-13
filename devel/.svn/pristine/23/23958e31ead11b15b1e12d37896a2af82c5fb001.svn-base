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
            alert('Sila masukkan " Maksud Pengambilan " terlebih dahulu.');
            $("#maksudpengambilan").focus();
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
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTSP'}">
            Maklumat Pendudukan Sementara
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTPT'}">
            Maklumat Pemulihan Tanah
            </c:if>
             <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PHLLP'}">
            Maklumat Hak Lalu Lalang Persendirian
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PHLLA'}">
            Maklumat Hak Lalu Lalang Awam
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PILDA'}">
            Maklumat Izin Lalu Di bawah Akta Bekalan Elektrik
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PHL'}">
            Maklumat Pembatalan Hak Lalu Lalang Awam/Persendirian
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTNB'}">
            Maklumat Izin Lalu Di bawah Akta Bekalan Elektrik
            </c:if>
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
             <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTSP'}">
                <label for="Maklumat Pengambilan">No Rujukan Surat :</label>
                <s:text name="permohonanRujukanLuar.noRujukan" size="30" id="norujukan" />
             </c:if>
             <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTPT' || actionBean.permohonan.kodUrusan.kod eq 'PHLLP'|| actionBean.permohonan.kodUrusan.kod eq 'PHLLA'|| actionBean.permohonan.kodUrusan.kod eq 'PHL'|| actionBean.permohonan.kodUrusan.kod eq 'PILDA'|| actionBean.permohonan.kodUrusan.kod eq 'PTNB'}">
                <label for="Maklumat Pengambilan">No Fail yang berkaitan :</label>
                <s:text name="permohonanRujukanLuar.noRujukan" size="30" id="norujukan" />
             </c:if>
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
            <label for="Trh_Surat_ Memohon">Tarikh permohonan :</label>
            <s:text name="tarikhRujukan" id="datepicker" class="datepicker" />
        </p>

        <%--<p>
            <label for="Maksud_Pengambilan">Maksud Pengambilan :</label>
           <s:textarea name="permohonan.sebab" rows="4" cols="35" id="maksudpengambilan"/>
        </p>--%>

        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTSP'}">
            <p>
                <label for="Maksud_Pengambilan">Maksud Penggunaan :</label>
               <s:textarea name="permohonan.sebab" rows="4" cols="35" id="maksudpengambilan" class="normal_text"/>
               <%--onkeyup="this.value=this.value.toUpperCase();"--%>
            </p>
        </c:if>

        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTPT'}">
            <p>
                <label for="Maksud_Pengambilan">Maksud Pemulihan :</label>
               <s:textarea name="permohonan.sebab" rows="4" cols="35" id="maksudpemulihan" class="normal_text"/>
            </p>
        </c:if>

        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PHLLP'}">
            <p>
                <label for="Maksud_Pengambilan">Maksud Hak Lalu Lalang Persendirian :</label>
               <s:textarea name="permohonan.sebab" rows="4" cols="35" id="maksudpemulihan" class="normal_text"/>
            </p>
        </c:if>
        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PHLLA'}">
            <p>
                <label for="Maksud_Pengambilan">Maksud Hak Lalu Lalang Awam :</label>
               <s:textarea name="permohonan.sebab" rows="4" cols="35" id="maksudpemulihan" class="normal_text"/>
            </p>
        </c:if>
        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PHL'}">
            <p>
                <label for="Maksud_Pengambilan">Aduan :</label>
               <s:textarea name="permohonan.sebab" rows="4" cols="35" id="maksudpemulihan" class="normal_text"/>
            </p>
        </c:if>
        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PILDA'|| actionBean.permohonan.kodUrusan.kod eq 'PTNB'}">
            <p>
                <label for="Maksud_Pengambilan">Maksud Penggunaan :</label>
               <s:textarea name="permohonan.sebab" rows="4" cols="35" id="maksudpemulihan" class="normal_text"/>
            </p>
        </c:if>
        
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
          <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTPT' || actionBean.permohonan.kodUrusan.kod eq 'PHLLP'|| actionBean.permohonan.kodUrusan.kod eq 'PHLLA'|| actionBean.permohonan.kodUrusan.kod eq 'PHL'|| actionBean.permohonan.kodUrusan.kod eq 'PILDA'|| actionBean.permohonan.kodUrusan.kod eq 'PTNB'}">
              <p>
                <label for="Maklumat Pengambilan">No Fail yang berkaitan :</label>${actionBean.permohonanRujukanLuar.noRujukan}&nbsp;
              </p>
          </c:if>
          <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTSP'}">
              <p>
                <label for="Maklumat Pengambilan">No Rujukan Surat :</label>${actionBean.permohonanRujukanLuar.noRujukan}&nbsp;
              </p>
          </c:if>
        <p>
            <label for="Trh_Surat_ Memohon">Tarikh permohonan :</label>${actionBean.tarikhRujukan}&nbsp;
        </p>
        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTSP'||actionBean.permohonan.kodUrusan.kod eq 'PILDA'}">
            <p>
                <label for="Maksud_Pengambilan">Maksud Penggunaan :</label>${actionBean.permohonan.sebab}&nbsp;
            </p>
        </c:if> 
         <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTPT'}">
            <p>
                <label for="Maksud_Pengambilan">Maksud Pemulihan :</label>${actionBean.permohonan.sebab}&nbsp;
            </p>
        </c:if>
         <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PHLLP'}">
            <p>
                <label for="Maksud_Pengambilan">Maksud Hak Lalu Lalang Persendirian :</label>${actionBean.permohonan.sebab}&nbsp;
            </p>
        </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PHLLA'}">
            <p>
                <label for="Maksud_Pengambilan">Maksud Hak Lalu Lalang Awam :</label>${actionBean.permohonan.sebab}&nbsp;
            </p>
        </c:if>
               <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PHL'}">
            <p>
                <label for="Maksud_Pengambilan">Maksud Aduan :</label>${actionBean.permohonan.sebab}&nbsp;
            </p>
        </c:if>
<%--            <p>
                 <label for="Tanah Asal">Setelah Pengambilan Balik :</label>${actionBean.permohonanPengambilan.selepasPengambilan}&nbsp;
            </p>--%>
      </c:if>
    </fieldset>
</div>
    </s:form>
