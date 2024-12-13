<%-- 
    Document   : dev_senarai_pihak_berkepentingan
    Created on : Jan 12, 2010, 5:55:32 PM        
    Author     : nursyazwani
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
    $(document).ready( function(){
        $('.empty').remove();

        var len2 = $(".alamat").length;

        for ( var i=0; i<len2; i++){
            var d = $('.x'+i).val();

            $('.nama'+i).bind('click',d, function(){
                window.open("${pageContext.request.contextPath}/pembangunan/pihak_berkepentingan?showEditPemohon&idPihak="+d, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=750");
            });
        }

    <%--$('#copy').click( function(){
        $('#page_div').html('');
        var url = '${pageContext.request.contextPath}/common/mohon_pihak?copy';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    });--%>
        });

        function popupExisting(){
            window.open("${pageContext.request.contextPath}/common/pihak", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }

        function addNew(){
            window.open("${pageContext.request.contextPath}/pembangunan/pihak_berkepentingan?pihakKepentinganPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }

    <%-- function addNewPemohon(){
         window.open("${pageContext.request.contextPath}/pembangunan/pihak_berkepentingan?pemohonPopup", "eTanah",
         "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
     }--%>

         function copy(){
             $('#page_div').html('');
             var url = '${pageContext.request.contextPath}/common/mohon_pihak?copy';
             $.get(url,
             function(data){
                 $('#page_div').html(data);
             },'html');
         }

         function refreshPagePihakBerkepentingan(){
             var url = '${pageContext.request.contextPath}/pengambilan/pihak_berkepentingan?refreshpage';
             $.get(url,
             function(data){
                 $('#page_div').html(data);
             },'html');
         }


         function addRowMohonPihak(nama, kp ,syer){
             //TODO: to be complete
             var rowNo = $('table#lineMP tr').length;
             $('table#lineMP > tbody').append("<tr id='x"+rowNo+"' class='x'><td class='rowNo'>"+rowNo
                 +"</td><td>"+nama+"</td><td>"+kp+"</td><td>"+syer+"</td><td>"+
                 "<img alt='Click to Delete' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem' id='rem"+
                 rowNo+"' onclick=\"remove(this.id,'line')\"></td></tr>");
         }

         function remove(val){
             if(confirm('Adakah anda pasti?')) {
                 var url = '${pageContext.request.contextPath}/common/mohon_pihak?delete&idPemohon='+val;
                 $.get(url,
                 function(data){
                     $('#page_div').html(data);
                 },'html');
             }
         }


         function removeSingle(idPemohon)
         {
             if(confirm('Adakah anda pasti?')) {
                 var url = '${pageContext.request.contextPath}/pengambilan/pihak_berkepentingan?deleteSingle&idPemohon='
                     +idPemohon;
                 $.get(url,
                 function(data){
                     $('#page_div').html(data);
                 },'html');}
         }

         function removeChanges(val){
             var answer = confirm("adakah anda pasti untuk Hapus?");
             if (answer){
                 var url = '${pageContext.request.contextPath}/pembangunan/pihak_berkepentingan?deleteChanges&idKkini='+val;
                 $.get(url,
                 function(data){
                     $('#page_div').html(data);
                 });
             }
         }

         function tambahBaru(){
             window.open("${pageContext.request.contextPath}/pengambilan/pihak_berkepentingan?pemohonPopup", "eTanah",
             "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
         }

         function addPemohon(){
             var len = $('.nama').length;
             for(var i=1; i<=len; i++){
                 var ckd = $('#chkbox'+i).is(":checked");
                 if(ckd){
                     var url = '${pageContext.request.contextPath}/pembangunan/pihak_berkepentingan?simpanPemohon&idPihak='+$('#chkbox'+i).val();
                     $.get(url,
                     function(data){
                         $('#page_div').html(data);
                     });
                 }
             }
         }

         function semakSyer(f){
             var q = $(f).formSerialize();
             $.post('${pageContext.request.contextPath}/pembangunan/pihak_berkepentingan?semakSyer',q,
             function(data){
                 if(data != ''){
                     alert(data);
                 }
             }, 'html');
         }

       

         function dopopup(i){

             var d = $('.x'+i).val();
             window.open("${pageContext.request.contextPath}/pengambilan/pihak_berkepentingan?showEditPemohon&idPihak="+d, "eTanah",
             "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
         }

</script>
<div class="subtitle displaytag">
    <s:messages/>
    <s:errors/>
    <s:form beanclass="etanah.view.stripes.pengambilan.PihakBerkepentinganActionBean">
        <fieldset class="aras1">
            <legend>Senarai Pemilik/Pihak Berkepentingan </legend>
            <div class="content" align="center">
                <label for="Maklumat Pengambilan">Bilangan Pihak Berkepentingan :&nbsp;${actionBean.size}</label>&nbsp;
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList2}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">

                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column title="No. Hakmilik" ><%--${line.hakmilik.kodHakmilik.kod}--%>${line.hakmilik.idHakmilik}</display:column>
                    <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
                    <display:column title="Bandar/Pekan/Mukim">${line.hakmilik.bandarPekanMukim.nama}
                        <br>${line.hakmilik.seksyen.nama}</display:column>
                    <%--No.KP/Co :&nbsp;<c:out value="${senarai.pihak.noPengenalan}"/>--%>
                    <display:column title="Tuan Tanah">
                        <c:set value="1" var="count"/>
                        <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                            <c:if test="${senarai.jenis.kod eq 'PM' && senarai.aktif eq 'Y'}">
                                <table border="0">
                                    <tr>
                                        <td><c:out value="${count}"/>)</td>
                                        <td><font style="text-transform:uppercase;"><c:out value="${senarai.pihak.nama}"/></font></td>
                                        <td>(<c:out value="${senarai.syerPembilang}/${senarai.syerPenyebut}"/>)</td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                        <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'B'}">
                                              <td>No.KP Baru :&nbsp;
                                                  <c:out value="${senarai.pihak.noPengenalan}"/>
                                              </td>
                                        </c:if>
                                        <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'L'}">
                                              <td>No.KP Lama :&nbsp;
                                                  <c:out value="${senarai.pihak.noPengenalan}"/>
                                              </td>
                                        </c:if>
                                        <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'S'}">
                                              <td>No.Syarikat :&nbsp;
                                                  <c:out value="${senarai.pihak.noPengenalan}"/>
                                              </td>
                                        </c:if>
                                        <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'D'}">
                                              <td>No.Pendaftaran :&nbsp;
                                                  <c:out value="${senarai.pihak.noPengenalan}"/>
                                              </td>
                                        </c:if>
                                        <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'F'}">
                                              <td>No.Paksa :&nbsp;
                                                  <c:out value="${senarai.pihak.noPengenalan}"/>
                                              </td>
                                        </c:if>
                                        <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'I'}">
                                              <td>No.Polis :&nbsp;
                                                  <c:out value="${senarai.pihak.noPengenalan}"/>
                                              </td>
                                        </c:if>
                                        <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'K'}">
                                              <td>No.MyKid :&nbsp;
                                                  <c:out value="${senarai.pihak.noPengenalan}"/>
                                              </td>
                                        </c:if>
                                        <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'N'}">
                                              <td>No.Bank :&nbsp;
                                                  <c:out value="${senarai.pihak.noPengenalan}"/>
                                              </td>
                                        </c:if>
                                        <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'P'}">
                                              <td>No.Passport :&nbsp;
                                                  <c:out value="${senarai.pihak.noPengenalan}"/>
                                              </td>
                                        </c:if>
                                        <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'T'}">
                                              <td>No.Tentera :&nbsp;
                                                  <c:out value="${senarai.pihak.noPengenalan}"/>
                                              </td>
                                        </c:if>
                                        <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'U'}">
                                              <td>No.Pertubuhan :&nbsp;
                                                  <c:out value="${senarai.pihak.noPengenalan}"/>
                                              </td>
                                        </c:if>
                                        <td>&nbsp;</td>
                                    </tr>
                                </table>
                                <c:set value="${count + 1}" var="count"/>
                            </c:if>
                        </c:forEach>

                    </display:column>
                    <%--<display:column title="Syer yang dimiliki">
                   <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                       <c:if test="${senarai.jenis.kod eq 'PM'}">
                          <c:out value="${senarai.syerPembilang}/${senarai.syerPenyebut}"/><br>
                          </c:if>
                   </c:forEach>
                   </display:column>--%>
                    <display:column title="Pihak Berkepentingan">
                        <%--<c:set value="1" var="count"/>
                    <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                        <c:if test="${senarai.jenis.kod ne 'PM'}">
                            <br>
                            <c:out value="${count}"/>)&nbsp;
                               <c:out value="${senarai.pihak.nama}"/>&nbsp;&nbsp;
                               <c:set value="${count + 1}" var="count"/><br>
                               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No.KP/Co:<c:out value="${senarai.pihak.noPengenalan}"/>
                                </c:if>
                    </c:forEach>--%>

                        <c:set value="1" var="count"/>
                        <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                            <c:if test="${senarai.jenis.kod ne 'PM' && senarai.aktif eq 'Y'}">
                                <table border="0">
                                    <tr>
                                        <td><c:out value="${count}"/>)</td>
                                        <td><c:out value="${senarai.nama}"/></td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                        <%--<td>No.KP/Co :&nbsp;<c:out value="${senarai.pihak.noPengenalan}"/></td>--%>
                                        <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'B'}">
                                              <td>No.KP Baru :&nbsp;
                                                  <c:out value="${senarai.pihak.noPengenalan}"/>
                                              </td>
                                        </c:if>
                                        <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'L'}">
                                              <td>No.KP Lama :&nbsp;
                                                  <c:out value="${senarai.pihak.noPengenalan}"/>
                                              </td>
                                        </c:if>
                                        <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'S'}">
                                              <td>No.Syarikat :&nbsp;
                                                  <c:out value="${senarai.pihak.noPengenalan}"/>
                                              </td>
                                        </c:if>
                                        <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'D'}">
                                              <td>No.Pendaftaran :&nbsp;
                                                  <c:out value="${senarai.pihak.noPengenalan}"/>
                                              </td>
                                        </c:if>
                                        <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'F'}">
                                              <td>No.Paksa :&nbsp;
                                                  <c:out value="${senarai.pihak.noPengenalan}"/>
                                              </td>
                                        </c:if>
                                        <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'I'}">
                                              <td>No.Polis :&nbsp;
                                                  <c:out value="${senarai.pihak.noPengenalan}"/>
                                              </td>
                                        </c:if>
                                        <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'K'}">
                                              <td>No.MyKid :&nbsp;
                                                  <c:out value="${senarai.pihak.noPengenalan}"/>
                                              </td>
                                        </c:if>
                                        <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'N'}">
                                              <td>No.Bank :&nbsp;
                                                  <c:out value="${senarai.pihak.noPengenalan}"/>
                                              </td>
                                        </c:if>
                                        <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'P'}">
                                              <td>No.Passport :&nbsp;
                                                  <c:out value="${senarai.pihak.noPengenalan}"/>
                                              </td>
                                        </c:if>
                                        <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'T'}">
                                              <td>No.Tentera :&nbsp;
                                                  <c:out value="${senarai.pihak.noPengenalan}"/>
                                              </td>
                                        </c:if>
                                        <c:if test="${senarai.pihak.jenisPengenalan.kod eq 'U'}">
                                              <td>No.Pertubuhan :&nbsp;
                                                  <c:out value="${senarai.pihak.noPengenalan}"/>
                                              </td>
                                        </c:if>
                                        <td>&nbsp;</td>
                                    </tr>
                                </table>
                                <c:set value="${count + 1}" var="count"/>
                            </c:if>
                        </c:forEach>
                    </display:column>
                    <display:column title="Jenis Pihak Berkepentingan">
                        <c:set value="1" var="count"/>
                        <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                            <c:if test="${senarai.jenis.kod ne 'PM' && senarai.aktif eq 'Y'}">
                                <table border="0">
                                    <tr>
                                        <td><c:out value="${count}"/>)</td>
                                        <td><c:out value="${senarai.jenis.nama}"/></td>
                                        <td>(<c:out value="${senarai.syerPembilang}/${senarai.syerPenyebut}"/>)</td>
                                    </tr>
                                </table>
                                <c:set value="${count + 1}" var="count"/>
                            </c:if>
                        </c:forEach>

                    </display:column>
                    <%--<display:column title="Bahagian yang dimiliki" >${line.syerPembilang}/${line.syerPenyebut}</display:column>--%>
                    <display:column title="Tarikh Pemilikan Didaftar">
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${line.infoAudit.tarikhMasuk}"/>
                    </display:column>
                    <%--<display:column property="jenis.nama" title="Jenis Pihak"/>--%>

                </display:table>

            </div>

        </fieldset>


    </s:form>
</div>
