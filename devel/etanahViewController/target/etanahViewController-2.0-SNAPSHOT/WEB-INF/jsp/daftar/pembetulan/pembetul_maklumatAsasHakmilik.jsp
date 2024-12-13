<%-- 
    Document   : pembetul_maklumatAsasHakmilik
    Created on : Dec 17, 2009, 10:59:59 AM
    Author     : mohd.fairul
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<%-- <script type="text/javascript"
       src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>--%>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/colorbox.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.colorbox.js"></script>

<style type="text/css">
    td.s{
        font-weight:bold;
        color:blue;
        text-align: right;
    }

    .arrow { background:transparent url(${pageContext.request.contextPath}/pub/images/arrows.png) no-repeat scroll 0px -16px; width:100%; height:16px; display:block;}
    .up { background-position:0px 0px;}
</style>

<script type="" language="javascript">
    $(document).ready( function(){
       
        $("#tutupAsal").click(function() {
            $("#ruangAsal").animate({"height": "toggle"}, { duration: 1000 });
            return false;
        });
        $("#tutupSblm").click(function() {
            $("#ruangSblm").animate({"height": "toggle"}, { duration: 1000 });
            return false;
        });
        $('#asas').toggle();
        $('#asal').toggle();
        $('#sebelum').toggle();
        $('#berikut').toggle();



    <%--set focus--%>
            $('input').focus(function() {
                $(this).addClass("focus");
            });

            $('input').blur(function() {
                $(this).removeClass("focus");
            });

            $('select').focus(function() {
                $(this).addClass("focus");
            });

            $('select').blur(function() {
                $(this).removeClass("focus");
            });
        });
        function popup(url)
        {
            params  = 'width='+screen.width;
            params += ', height='+screen.height;
            params += ', top=0, left=0'
            params += ', fullscreen=yes';
            params += ', directories=no';
            params += ', location=no';
            params += ', menubar=no';
            params += ', resizable=no';
            params += ', scrollbars=yes';
            params += ', status=no';
            params += ', toolbar=no';
            newwin=window.open(url,'PopUp', params);
            if (window.focus) {newwin.focus()}
            return false;
        }

        function showHide(divID,buttonID,buttonTitle){
   
            $('#'+divID).html('<table class="tablecloth">\n\
           <tr><th>'+buttonTitle+'</th> <th>Simpan</th> <th>Tutup</th></tr>\n\
           <tr><td><input type="text" name="'+buttonID+'" title="Masukan '+buttonTitle+'"/></td>\n\
           <td><div align="center"><input type="image" src="../images/save.gif" name="image" style="border: 0px;"/></td></div>\n\
           <td><div align="center"><img onclick="hideMe(&#39'+divID+'&#39);" src="../images/not_ok.gif" name="image" style="border: 0px;" id="tutup" onmouseover="this.style.cursor=&#34pointer&#34;"/></div></td></tr></table>');

            $('#'+divID).show();
         
        }

        function hideMe(divID){
            $('#'+divID).hide('slow');
        }

        function showMe(thID){
               
            $('#'+thID).toggle();
            $('.'+thID).find(".arrow").toggleClass("up");
        }

            
        function refreshME(){
              
    <%--alert("data");--%>
    <%--var url= '${pageContext.request.contextPath}/daftar/pembetulan/betul?testRefresh';
    $.post(url);--%>
            $('#page_div').load('${pageContext.request.contextPath}/daftar/pembetulan/betul?asasHakmilik');
        }

        function timerME(){
            setTimeout('refreshME()',1000);
    <%--alert("teasdad");--%>
                     
        }

          function removeChanges(val){
            var answer = confirm("adakah anda pasti untuk Hapus?");
            if (answer){
                var url = '${pageContext.request.contextPath}/daftar/pembetulan/betul?deleteBetulHakmilik&idHakmilik='+val;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                });
            }
        }

        function removeChangesAsal(val){
            var answer = confirm("adakah anda pasti untuk Hapus?");
            if (answer){
                var url = '${pageContext.request.contextPath}/daftar/pembetulan/betul?deleteChangesAsal&idHakmilikAsal='+val;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                });
            }
        }



        function removeChangesSblm(val){
            var answer = confirm("adakah anda pasti untuk Hapus?");
            if (answer){
                var url = '${pageContext.request.contextPath}/daftar/pembetulan/betul?deleteChangesSblm&idHakmilikSebelum='+val;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                });
            }
        }
      
</script>

<s:form beanclass="etanah.view.stripes.nota.pembetulanActionBean" class="form1">

    <s:messages  />
    <s:errors />
    <div class="content" align="center" id="tanahMilik">
        <table class="tablecloth" width="70%" style="margin-left: auto; margin-right: auto;">
            <tr onclick="showMe('asas')" onmouseover="this.style.cursor='pointer'; this.style.text" class="asas"><th><span class="arrow">Maklumat Asas Hakmilik</span></th></tr>
            <tr id="asas"><td>
                    <fieldset class="aras1" >
                        <legend>
                        </legend>
                        <p style="color:red">
                            *Sila pilih ID Hakmilik untuk membuat pembetulan.Kemudian klik(<img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem' id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';"/>)<br/>
                        </p>
                        <div class="content" align="center">
                            <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}"  cellpadding="0" cellspacing="0"
                                           id="line">
                                <display:column title="Bil">${line_rowNum}</display:column>
                                <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                                <display:column title="Baiki / Semak">
                                    <div align="center">
                                        <%--<img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem' id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="hakmilikAsas('${line.hakmilik.idHakmilik}');"/>--%>
                                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem' id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betul?popupAsas&idH=${line.hakmilik.idHakmilik}');"/>
                                        <%--<img alt='Klik Untuk Kemaskini' border='0' title="Klik Membuat Pembetulan" src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betul?messagerME')"/>--%>
                                    </div>
                                </display:column>
                                <display:column title="Hapus Pembetulan">
                                    <div align="center">
                                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                             id='rem${line_rowNum}' onclick="removeChanges('${line.hakmilik.idHakmilik}')" onmouseover="this.style.cursor='pointer';" >
                                    </div>
                                </display:column>
                            </display:table>
                            <br/>
                            <br/>
                            &nbsp;
                        </div>

                    </fieldset>
                </td></tr>
                <%--Hakmilik Asal Start here--%>
            <tr onclick="showMe('asal')" onmouseover="this.style.cursor='pointer';" class="asal"><th><span class="arrow">Maklumat Hakmilik Asal</span></th></tr>
            <tr id="asal"><td>
                    <fieldset class="aras1">
                        <legend>
                        </legend>
                        <p style="color:red">
                            *Pilih id Hakmilik Asal, kemudian klik (<img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem' id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';"/>) untuk baiki atau klik (  <img alt='Klik Untuk Kemaskini' title="Klik Untuk Tambah" border='0' src='${pageContext.request.contextPath}/pub/images/tambah.png' class='rem'id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';"/>) untuk tambah hakmilik asal<br/>
                        </p>
                        <div class="content" align="center">
                            <table class="tablecloth">
                                <tr><th>Bil</th><th>ID Hakmilik</th><th>ID Hakmilik Asal</th><th>Baiki</th><th>Tambah/Mansuh</th></tr>
                                <c:set var="count" value="1"/>
                                <c:forEach items="${actionBean.hakmilikPermohonanList}" var="listPH" varStatus="statusPH">
                                    <tr>
                                        <td>${count}</td>
                                        <td>${listPH.hakmilik.idHakmilik}</td>
                                        <c:if test="${fn:length(listPH.hakmilik.senaraiHakmilikAsal) == 0}">
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </c:if>
                                        <c:forEach items="${listPH.hakmilik.senaraiHakmilikAsal}" var="listHA" varStatus="asalStatus">

                                            <c:if test="${asalStatus.first}">
                                                <td>${listHA.hakmilikAsal}</td>
                                            </c:if>
                                            <c:if test="${!asalStatus.first}">
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>${listHA.hakmilikAsal}</td>

                                            </c:if>

                                            <td>
                                                <div align="center">
                                                    <img alt='Klik Untuk Kemaskini' border='0' title="Klik Membuat Pembetulan" src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betul?popupAsal&idH=${listHA.hakmilik.idHakmilik}&idHA=${listHA.hakmilikAsal}')"/>

                                                </div>
                                            </td>
                                            <td>
                                                <div align="center">
                                                     <img alt='Klik Untuk Kemaskini' border='0' title="Klik Membuat Pembetulan" src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betul?popupDeleteHakmilikAsal&idH=${listHA.hakmilik.idHakmilik}&idHA=${listHA.hakmilikAsal}')"/>

                                                </div>
                                            </td>
                                        </tr>


                                    </c:forEach>

                                    <tr class="test"><td colspan="2">&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td> <div align="center">
                                                <%-- <c:if test="${fn:length(actionBean.listHakmilikAsal) == 0}"><img alt='Klik Untuk Kemaskini' title="Klik Untuk Tambah" border='0' src='${pageContext.request.contextPath}/pub/images/tambah.png' class='rem'id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betul?tambahHakmilikAsal&idH=${listPH.hakmilik.idHakmilik}')"/></c:if> --%>
                                                <img alt='Klik Untuk Kemaskini' title="Klik Untuk Tambah" border='0' src='${pageContext.request.contextPath}/pub/images/tambah.png' class='rem'id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betul?tambahHakmilikAsal&idH=${listPH.hakmilik.idHakmilik}')"/>
                                            </div>
                                        </td>
                                    </tr>
                                    <c:set var="count" value="${count+1}"/>
                                </c:forEach>

                            </table>
                            <c:if test="${fn:length(actionBean.listHakmilikAsal) > 0}">
                                <legend>
                                    Penambahan Hakmilik Asal
                                </legend>
                                <p align="center"><label></label>
                                    <display:table class="tablecloth" name="${actionBean.listHakmilikAsal}"
                                                   pagesize="10" cellpadding="0" cellspacing="0" id="line">
                                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                        <display:column property="idHakmilikAsal" title="ID Hakmilik"/>
                                        <%--<display:column property="tarikhDaftarHakmilikAsal" title="Tarikh Daftar" format="{0,date,dd-MM-yyyy}"/>--%>
                                        <display:column title="Hapus">
                                        <div align="center">
                                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                 id='rem${line_rowNum}' onclick="removeChangesAsal('${line.idBetul}')" onmouseover="this.style.cursor='pointer';">
                                        </div>
                                    </display:column>
                                </display:table>
                                </p>
                            </c:if>
                            <br/>

                        </div>

                    </fieldset>
                </td></tr>
            <tr onclick="showMe('sebelum')" onmouseover="this.style.cursor='pointer';" class="sebelum"><th><span class="arrow">Maklumat Hakmilik Sebelumkini</span></th></tr>
            <tr id="sebelum"><td>
                    <fieldset class="aras1">
                        <legend>

                        </legend>
                        <p style="color:red">
                            *Pilih id Hakmilik Sebelumkini, kemudian klik (<img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem' id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';"/>) untuk baiki atau klik (  <img alt='Klik Untuk Kemaskini' title="Klik Untuk Tambah" border='0' src='${pageContext.request.contextPath}/pub/images/tambah.png' class='rem'id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';"/>) untuk tambah hakmilik sebelum<br/>
                        </p>
                        <div class="content" align="center">
                            <table class="tablecloth">
                                <tr><th>Bil</th><th>ID Hakmilik</th><th>ID Hakmilik Sebelumkini</th><th>Baiki</th><th>Tambah/Mansuh</th></tr>
                                <c:set var="count" value="1"/>
                                <c:forEach items="${actionBean.hakmilikPermohonanList}" var="listPH" varStatus="statusPH">

                                    <tr>
                                        <td>${count}</td>
                                        <td>${listPH.hakmilik.idHakmilik}</td>
                                        <c:if test="${fn:length(listPH.hakmilik.senaraiHakmilikSebelum) == 0}">
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>

                                        </c:if>
                                        <c:forEach items="${listPH.hakmilik.senaraiHakmilikSebelum}" var="listHS" varStatus="sebelumStatus">

                                            <c:if test="${sebelumStatus.first}">
                                                <td>${listHS.hakmilikSebelum}</td>
                                            </c:if>
                                            <c:if test="${!sebelumStatus.first}">
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>${listHS.hakmilikSebelum}</td>

                                            </c:if>

                                            <td>
                                                <div align="center">
                                                    <img alt='Klik Untuk Kemaskini' border='0' title="Klik Membuat Pembetulan" src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betul?popupSebelumkini&idH=${listHS.hakmilik.idHakmilik}&idHS=${listHS.hakmilikSebelum}')"/>

                                                </div>
                                            </td>
                                            <td>
                                                <div align="center">
                                                    <img alt='Klik Untuk Kemaskini' border='0' title="Klik Membuat Pembetulan" src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betul?popupSebelumkini&idH=${listHS.hakmilik.idHakmilik}&idHS=${listHS.hakmilikSebelum}')"/>

                                                </div>
                                            </td>
                                        </tr>


                                    </c:forEach>

                                    <tr class="test"><td colspan="2">&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td> <div align="center">
                                                <img alt='Klik Untuk Kemaskini' title="Klik Untuk Tambah" border='0' src='${pageContext.request.contextPath}/pub/images/tambah.png' class='rem'id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betul?tambahHakmilikSebelum&idH=${listPH.hakmilik.idHakmilik}')"/>
                                            </div>
                                        </td>
                                    </tr>
                                    <c:set var="count" value="${count+1}"/>
                                </c:forEach>

                            </table>
                            <%--<c:forEach items="${actionBean.permohonanbetulHakmilik}" var="listbh" varStatus="statusPH">--%>
                            <c:if test="${fn:length(actionBean.listHakimilSblmMohon) > 0}">
                                <legend>
                                    Penambahan Hakmilik Sebelum
                                </legend>
                                <p align="center"><label></label>
                                    <display:table class="tablecloth" name="${actionBean.listHakimilSblmMohon}"
                                                   pagesize="10" cellpadding="0" cellspacing="0" id="line">
                                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                        <display:column property="hakmilikSejarah" title="ID Hakmilik"/>
                                        <display:column title="Hapus">
                                        <div align="center">
                                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                 id='rem${line_rowNum}' onclick="removeChangesSblm('${line.idHakmilikSebelumPermohonan}')" onmouseover="this.style.cursor='pointer';">
                                        </div>
                                    </display:column>
                                </display:table>
                                </p>
                            </c:if>
                            <br/>
                        </div>

                    </fieldset>
                </td></tr>
                <%--End Hakmilik Sebelumkini--%>
                <%--Start Hakmilik Berikut--%>
                <%-- <tr onclick="showMe('berikut')" onmouseover="this.style.cursor='pointer';" class="berikut"><th><span class="arrow">Maklumat Hakmilik Berikut</span></th></tr>
                 <tr id="berikut"><td>
                         <fieldset class="aras1">
                             <legend>
                             </legend>
                              <p style="color:red">
                                 *Pilih id Hakmilik Berikut, kemudian klik (<img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem' id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';"/>) untuk baiki atau klik (  <img alt='Klik Untuk Kemaskini' title="Klik Untuk Tambah" border='0' src='${pageContext.request.contextPath}/pub/images/tambah.png' class='rem'id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';"/>) untuk tambah hakmilik asal<br/>
                             </p>
                             <div class="content" align="center">

                  will remove when comfirn
                        <table class="tablecloth">
                            <tr><th>Bil</th><th>ID Hakmilik</th><th>ID Hakmilik Berikut</th><th>Baiki</th><th>Tambah/Mansuh</th></tr>
                            <c:set var="count" value="1"/>
                            <c:forEach items="${actionBean.hakmilikPermohonanList}" var="listPH" varStatus="statusPH">

                                <tr>
                                    <td>${count}</td>
                                    <td>${listPH.hakmilik.idHakmilik}</td>
                                    <c:if test="${fn:length(listPH.hakmilik.senaraiHakmilikSebelum) == 0}">
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>

                                    </c:if>
                                    <c:forEach items="${listPH.hakmilik.senaraiHakmilikSebelum}" var="listHS" varStatus="sebelumStatus">

                                        <c:if test="${sebelumStatus.first}">
                                            <td>${listHS.hakmilikSebelum.idHakmilik}</td>
                                        </c:if>
                                        <c:if test="${!sebelumStatus.first}">
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>${listHS.hakmilikSebelum.idHakmilik}</td>

                                        </c:if>

                                        <td>
                                            <div align="center">
                                                <img alt='Klik Untuk Kemaskini' border='0' title="Klik Membuat Pembetulan" src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betul?popupSebelumkini&idH=${listHS.hakmilik.idHakmilik}&idHS=${listHS.hakmilikSebelum.idHakmilik}')"/>


                                            </div>
                                        </td>
                                        <td>
                                            <div align="center">
                                                <img alt='Klik Untuk Mansuh' border='0' title="Klik Untuk Mansuh" src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betul?popupSebelumkini&idH=${listHS.hakmilik.idHakmilik}&idHS=${listHS.hakmilikSebelum.idHakmilik}')"/>

                                            </div>
                                        </td>
                                    </tr>


                                </c:forEach>

                                <tr class="test"><td colspan="2">&nbsp;</td>
                                    <td>&nbsp;</td>
                                    <td>&nbsp;</td>
                                    <td> <div align="center">
                                            <img alt='Klik Untuk Kemaskini' title="Klik Untuk Tambah" border='0' src='${pageContext.request.contextPath}/pub/images/tambah.png' class='rem'id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betul?tambahHakmilikSebelum&idH=${listPH.hakmilik.idHakmilik}')"/>
                                        </div>
                                    </td>
                                </tr>
                                <c:set var="count" value="${count+1}"/>
                            </c:forEach>

                        </table>

                        <br/>
                    </div>

                </fieldset>
            </td></tr>--%>
        </table>
    </div>
</s:form>