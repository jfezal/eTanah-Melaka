<%-- 
    Document   : maklumat_pengambilan
    Created on : 13-Jan-2010, 10:30:24
    Author     : nordiyana
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>


<script type="text/javascript">
    function kedesakan1(idPermohonan, flag) {
//        alert(flag);
        doBlockUI();
        var url = '${pageContext.request.contextPath}/pengambilan/maklumat_pengambilan?kedesakan&idPermohonan=' + idPermohonan + '&flag=' + flag;
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'html',
            error: function(xhr, ajaxOptions, thrownError) {
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success: function(data) {
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });
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

<s:form beanclass="etanah.view.stripes.pengambilan.MaklumatPengambilanActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="permohonanRujukanLuar.idRujukan"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Pengambilan</legend>
            <c:if test="${edit}">
                <%--  <c:if test="${actionBean.permohonan.kodUrusan.kod eq '831A'}">
                      <p>
                   <label for="status permohonan">Jenis Permohonan :</label>
                   <s:radio name="" value="Segera"/>&nbsp;Segera
                   <s:radio name="" value="Tidak Segera"/>&nbsp;Tidak Segera
                   </p>
                  </c:if>--%>

                <p>
                    <label for="Maklumat Pengambilan">No Rujukan Surat :</label>
                    <s:text name="noRujukan" size="30" id="norujukan" />
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
                <!--                <p>
                                    <label for="Maksud_Pengambilan">Maksud Pengambilan :</label>
                <s:textarea name="permohonan.sebab" rows="4" cols="35" id="maksudpengambilan"/>
            </p>-->
                <p>
                    <label for="Tujuan Pengambilan">Tujuan Pengambilan :</label>
                    <s:textarea name="tujuanPengambilan" rows="4" cols="35" id="tujuanPengambilan"/>
                </p>
                <p>
                    <label for="Tanah Asal">Setelah Pengambilan Tanah :</label>
                    <s:checkbox name="hakmilikSambungan" value="HS"/>&nbsp; Hakmilik<br>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:checkbox name="tanahRizab" value="TR"/>&nbsp; Rizab<br>

                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:checkbox name="tanahKerajaan" value="TK"/>&nbsp; Tanah Kerajaan<br>

                </p>
                <br>

                <p align="center">
                    Adakah Permohonan Ini mempunyai Kedesakan :
                    <c:if test="${actionBean.flag eq 'Y'}">
                         &nbspAda Kedesakan &nbsp &nbsp
                        <s:radio name="kedesakanvalue" id="flag"  value="T" onclick="kedesakan1('${line.mohonHakmilik.permohonan.idPermohonan}','T');"/> Tiada Kedesakan
                    </c:if>
                    <c:if test="${actionBean.flag eq 'T'}">
                        <s:radio name="kedesakanvalue" id="flag"  value="Y"   onclick="kedesakan1('${permohonan.idPermohonan}','Y');"/> Ada Kedesakan &nbsp &nbsp
                         Tiada Kedesakan
                    </c:if>
                    <c:if test="${actionBean.flag eq null}">
                        <s:radio name="kedesakanvalue" id="flag"  value="Y"   onclick="kedesakan1('${permohonan.idPermohonan}','Y');"/> Ada Kedesakan &nbsp &nbsp
                        <s:radio name="kedesakanvalue" id="flag"  value="T" onclick="kedesakan1('${line.mohonHakmilik.permohonan.idPermohonan}','T');"/> Tiada Kedesakan
                    </c:if>



                </p>
                <br>
                <p>
                    <label>&nbsp;</label>
                    <s:button name="savePengambilanInfo" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"  />
                    <%-- onclick="save1(this.name, this.form);"--%>
                </p>
                <%-- ${actionBean.hakmilikSambungan}
                 ${actionBean.tanahRizab}
                 ${actionBean.tanahKerajaan}--%>
                <c:if test="${actionBean.statusLepasPengambilan.kodStatusTanahLepasPengambilan eq 'HS'}">${actionBean.hakmilikSambungan}yyy</c:if>
                <c:if test="${actionBean.statusLepasPengambilan.kodStatusTanahLepasPengambilan eq 'TR'}">${actionBean.tanahRizab}rrrr</c:if>
                <c:if test="${actionBean.statusLepasPengambilan.kodStatusTanahLepasPengambilan eq 'TK'}">${actionBean.tanahKerajaan}kkk</c:if>

            </c:if>
            <c:if test="${!edit}">
                <%--<c:if test="${actionBean.permohonan.kodUrusan.kod eq '831A'}">
                <p>
                  <label for="Maklumat Pengambilan">Jenis Permohonan :</label>Segera&nbsp;
                </p>
                </c:if>--%>
                <p>
                    <label for="Maklumat Pengambilan">No Rujukan Surat :</label><c:if test="${actionBean.noRujukan ne null}">${actionBean.noRujukan}</c:if>&nbsp;

                    </p>
                    <p>
                        <label for="Trh_Surat_ Memohon">Tarikh Surat Memohon :</label><c:if test="${actionBean.tarikhRujukan ne null}">${actionBean.tarikhRujukan} </c:if> &nbsp;

                    </p>
                    <p>
                        <label for="Maksud_Pengambilan">Maksud Pengambilan :</label><c:if test="${actionBean.tujuanPengambilan ne null}"> ${actionBean.tujuanPengambilan}</c:if> &nbsp;

                    <%--<s:textarea name="permohonan.sebab" rows="4" cols="35" --%>
                </p>
                <p>
                    <label for="Tanah Asal">Setelah Pengambilan Balik :</label>
                    <c:if test="${actionBean.hakmilikSambungan eq 'HS'}">Hakmilik Sambungan,</c:if>
                    <c:if test="${actionBean.tanahRizab eq 'TR'}">Tanah Rizab,</c:if>
                    <c:if test="${actionBean.tanahKerajaan eq 'TK'}">Tanah Kerajaan</c:if>
                    <%-- ${actionBean.hakmilikSambungan}
               ${actionBean.tanahRizab}
               ${actionBean.tanahKerajaan}--%>
                </p>

            </c:if>      
        </fieldset>
    </div>
</s:form>
