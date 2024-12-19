<%-- 
    Document   : kemasukanAduan
    Created on : May 28, 2013, 11:57:56 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<html>
    <head><title>Aduan Pengambilan</title>
        <link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                dialogInit('Carian Hakmilik');
            <%--       var pguna = ${actionBean.pengguna.idPengguna};
                   alert(pguna);--%>
                           var negeri = "${actionBean.kodNegeri}";
                           if(negeri == "melaka"){
                               $('#akaun').focus();
                           }else{
                               $('#hakmilik').focus();
                           }
                       });
                       function zeroPad(num,count)
                       {
                           var numZeropad = num + '';
                           while(numZeropad.length < count) {
                               numZeropad = "0" + numZeropad;
                           }
                           return numZeropad;
                           $("#noLot").val(numZeropad);
                       }
                       function change(){
                           var a = $("#kod").val();
                           var b = a.toUpperCase();

                           $("#kod").val(b);
                       }

                       //                       function filterDaerah(kodDaerah){
                       //                           var url = '${pageContext.request.contextPath}/common/carian_hakmilik?penyukatanBPM&daerah='+kodDaerah;
                       //                           $.get(url,
                       //                           function(data){
                       //                                $('#page_div').html(data);
                       //                           },'html');
                       //                       }
                       function filterDaerah(kodDaerah,frm){
                           //                            var jabatan = f.elements['pguna.kodJabatan.kod'].value ;
                           var url = '${pageContext.request.contextPath}/common/carian_hakmilik?penyukatanBPM&daerah='+kodDaerah;
                           frm.action = url;
                           frm.submit();

                       }
                       function filterBPM(kodBPM,frm){
                           var daerah = $('#daerah').val();
                           var url = '${pageContext.request.contextPath}/common/carian_hakmilik?penyukatanSeksyen&bandarPekanMukim='+kodBPM+'&daerah'+daerah;
                           frm.action = url;
                           frm.submit();
                       }
        </script>
        <script type="text/javascript">
            function popup(id){
                window.open("${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?infoPembayar&idPegang="+id, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=300");
            }


            function refresh1(v){
                var url = '${pageContext.request.contextPath}/common/carian_hakmilik?papar&idHakmilik='+v;
                $.get(url,
                function(data){
                    $('#daerah').html(data);
                },'html');
            }

            function doSubmit(e,f,g) {
                //alert('test');
                //var q = $('#carian_hakmilik').formSerialize();
                //alert(h);
                //alert(e);
                //alert(g);
                //while (h.parentNode && h.parentNode.tagName != "FORM"){
                //    h = h.parentNode;
                //}
                //var f = $('#carian_hakmilik').
                //svar f = h.parentNode;
                var q = $(f).formSerialize();
                //var q = $('#carian_hakmilik').serialize();
                f.action = f.action + '?' + e + '&idHakmilik=' + g + '&popup&' + q;
                //alert(f.action.toString());
                f.submit();
            }

            function completeId(id){
                var l = id.length;
                if(l > 0){
                    var lengthId = 8;
                    var i = "";
                    for(var x=0;x<(lengthId-l);x++){
                        i = i+'0';
                    }
                    var noHakmilik = i+id;
                    $("#noHakmilik").val(noHakmilik);
                }
            }
         function p(v){
            <%--alert(v);--%>
                $.blockUI({
                    message: $('#displayBox'),
                    css: {
                        top:  ($(window).height() - 50) /2 + 'px',
                        left: ($(window).width() - 50) /2 + 'px',
                        width: '50px'
                    }
                });

                $.get("${pageContext.request.contextPath}/pengambilan/carian_aduan?pihakDetails&idMH="+v,
                function(data){
                    <%--alert(idMH);--%>
                    $("#perincianHakmilik").show();
                    $("#perincianHakmilik").html(data);
                    $.unblockUI();
                });

            }

        function removeNilai(id)
        {
                window.open("${pageContext.request.contextPath}/pengambilan/carian_aduan?pihakDetails&idMH="+id, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=1500,scrollbars=yes");
        }
        function refreshPagePBT(id){
           <%-- var url = '${pageContext.request.contextPath}/pengambilan/carian_aduan?refreshpage&idMH="+id';
            $.get(url,--%>
            $.get("${pageContext.request.contextPath}/pengambilan/carian_aduan?refreshpage&idMH="+id,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
          function ajaxLink(link, update) {
        $.get(link, function(data) {
            $(update).html(data);
            $(update).show();
        });
        return false;
    }

        </script>


    </head>
    <body>
        <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
        <s:form beanclass="etanah.view.stripes.pengambilan.AduanActionBean" id="carian_aduan">
        <s:hidden id="selectedMH" name="selectedMH"/>
            <div class="subtitle" id="hakmilik_details">
                <s:errors/>
                <s:messages/>

                <fieldset class="aras1">
                    <legend>
                        Kemasukan Permohonan Aduan
                    </legend>
                    <div class="instr-fieldset">
                        <font color="red">PERINGATAN:</font>Sila Masukan Maklumat Berikut.
                    </div>&nbsp;
                    <p>
                        <label >ID Hakmilik :</label>
                        <s:text id="hakmilik" name="idHakmilik"  size="31" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                 <%--   <p>
                        <label> Nama Pemilik :</label>
                        <s:text name="namaPemilik" maxlength="50" size="31" onkeyup="this.value=this.value.toUpperCase();" />
                    </p>
                    <p>
                        <label> No Pengenalan Pemilik :</label>
                        <s:text name="noPengenalan"  maxlength="30" size="31" />&nbsp;<font size="1" color="red"> (cth : 840913117626)</font>
                    </p>--%>


                    <div align="right">
                        <s:submit name="search" value="Cari" class="btn" />
                        <s:button name=" " value="Isi Semula" class="btn" onclick="clearText('carian_hakmilik');" />
                    </div>
                </fieldset>

            </div>
            <c:if test="${actionBean.flag}">
                <br>
                <div class="subtitle" id="hakmilik_details">
                    <fieldset class="aras1">
                        <legend>
                            Senarai Permohonan
                        </legend>

                        <c:set var="row_num" value="${actionBean.__pg_start}"/>
                        <div class="content" align="center">
                            <%-- <display:table class="tablecloth" name="${actionBean.list}"
                                           pagesize="10" cellpadding="0" cellspacing="0" requestURI="/common/carian_hakmilik" id="line">--%>
                            <display:table class="tablecloth" name="${actionBean.listHakmilik}"
                                           pagesize="10" cellpadding="0" cellspacing="0"
                                           requestURI="/common/carian_hakmilik" id="line"
                                           sort="external" size="${actionBean.__pg_total_records}" partialList="true">
                                <c:set var="row_num" value="${row_num+1}"/>
                                  <display:column title="Pilih">
                                     <s:radio name="selectedMH" value="${line.id}"/>
                                </display:column>
                                <display:column title="Bil" sortable="true" > <div align="center">${row_num}</div></display:column>
                                <display:column property="hakmilik.idHakmilik" title="ID Hakmilik"  />
                                <display:column title="No.Perserahan/Permohonan">${line.permohonan.idPermohonan}</display:column>
                                <display:column title="Projek">${line.permohonan.sebab}</display:column>
                                <%--<display:column title="Pilih"><s:button name="papar" value="Papar" class="btn" onclick="removeNilai('${line.id}');return false;"/><s:param name="idMH" value="${line.id}"/></display:column>--%>
                                <%--<display:column title="Select"><s:link beanclass="etanah.view.stripes.pengambilan.AduanActionBean"
                                                                    event="pihakDetails" onclick="return ajaxLink(this,'#hakmilik_details');" >
                                                                    <s:param name="idMH" value="${line.id}"/>Pilih
                                                                </s:link>
                                </display:column>--%>

                            </display:table>
                        </div>
                    </fieldset>
            
                </div>
                            <div class="subtitle">
                    <div align="right">
                         <s:submit name="seterus" value="Seterusnya" class="btn" id="next"/>

                    </div>
                     </div>
                
            </c:if>
                 
            
                    
        </s:form>
    
    </body>
</html>
