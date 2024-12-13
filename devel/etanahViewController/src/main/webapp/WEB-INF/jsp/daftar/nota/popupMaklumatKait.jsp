<%--
    Document   : popup_kodsyaratnyata
    Created on : Dec 28, 2009, 7:34:09 PM
    Author     : mohd.fairul
--%>

<html>
    <head>
        <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
        <%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kemasukan Berkaitan Hakmilik</title>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>
        <script src="<%= request.getContextPath()%>/js/ui.datepicker-ms.js" type="text/javascript"></script>
        <s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>

        <script type="text/javascript">
     
            $(document).ready( function(){
     
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

                <%--hide--%>
                    $('.wait').hide();
            });



            function popupCarian(pathCall)
            {
                var url ="${pageContext.request.contextPath}/daftar/nota/nota_daftar?"+pathCall;
                window.open(url,"eTanah1","status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
            }
            function add(event,f){
                var id =  ${actionBean.id};//$('input:text[name=id]').val();
                var cukaiBaru = $('input:text[name=cukaiBaru]').val();
                if(cukaiBaru =="")
                {
                    cukaiBaru = "undefined"
                }
                var kodSyaratNyata = $('input:text[name=kodSyaratNyata]').val();
                var kodSekatan = $('input:text[name=kodSekatan]').val();
                   
                var kodOUM = $('#kodLuas').val();
                if(kodOUM =="")
                {
                    kodOUM = "undefined"
                }
                var katTanah = $('#katTanah').val();
                var luas = $('input:text[name=luas]').val();
                if(luas =="")
                {
                    luas = "undefined"
                }
                var q = $(f).formSerialize();
                var url = f.action + '?' + event +'&id='+id
                    +'&cukaiBaru='+cukaiBaru+'&luas='+luas+'&kodSyaratNyata='+kodSyaratNyata+
                    '&kodSekatan='+kodSekatan+'&kodLuas='+kodOUM+'&katTanah='+katTanah;

               <%--if(validateNum() == true){--%>
                $.post(url,q,
                function(data){
                    $('#page_div',opener.document).html(data);

                },'html');
                setTimeout("window.self.close()",2000);
                <%--}--%>
                <%--else{alert("Sila betulkan kesalahan")}--%>

            }



        <%--validation for luas--%>
                function validateNum(){
                                 var id = "luas";
                                var regex = /^\d{1,9}\.?\d{0,8}$/;
                                var valueDimasukan = document.getElementById(id).value;
                                var valueAsal = '${actionBean.hakmilik.luas}';
            <%--alert(document.getElementById(id).value);--%>
                    if(document.getElementById(id).value != ""){
                        if (document.getElementById(id).value.search(regex) ==-1) {


      
                            $('.luas').html(" <img alt='Padam Isian' border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'  style='vertical-align:middle;'><span  style='color:red; margin:0px;'> Terdapat Kesalahan Pada Kemasukkan Luas</span></img>");
                          $('.wait').hide();
                            document.getElementById(id).value = '';
                            return false;

                        } else {
                            if(parseFloat(valueDimasukan) > parseFloat(valueAsal)){
                                $('.luas').html(" <img alt='TiaDa' border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'  style='vertical-align:middle;'><span  style='color:red; margin:0px;'> Luas Dimasukkan Lebih Besar Daripada Luas Keseluruhan</span></img>");
                                $('.wait').hide();
                                document.getElementById(id).value = '';
                            }
                            else{
                                $('.luas').html(" <img alt='TIada' border='0' src='${pageContext.request.contextPath}/pub/images/ok.png' class='rem'  style='vertical-align:middle;'><span  style='color:green; margin:0px;'> Maklumat Dimasukkan Memenuhi Ciri</span></img>");
                              $('.wait').show();
                              return true;}
                        }
                    }
                    else{  $('.luas').html("");
               return true;
               }
                }

        </script>
    </head>
    <body>
        <s:form beanclass="etanah.view.stripes.nota.NotaDaftarActionBean" id="myform">

            <s:messages/>
            <s:errors/>
            <div class="subtitle">

                <fieldset class="aras1">
                    <legend>Maklumat Hakmilik</legend>
                    <p>
                        <label>ID Permohonan :</label> ${actionBean.permohonan.idPermohonan}

                    </p>
                    <p>
                        <label>ID Hakmilik :</label> ${actionBean.hakmilik.idHakmilik}
                        <%--<s:text name="idHakmilik" id="idHakmilik" disabled="true"/>--%>
                    </p>
                    <%-- <p>
                         <label>ID Urusan :</label> <s:text name="id" id="id" disabled="true"/>
                         <s:hidden name="id" id="id"/>
                     </p>--%>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'ABT-D'
                                  or actionBean.permohonan.kodUrusan.kod eq 'ABT-A'
                                  or actionBean.permohonan.kodUrusan.kod eq 'ABT-K'
                                  or actionBean.permohonan.kodUrusan.kod eq 'ABT'
                                  or actionBean.permohonan.kodUrusan.kod eq 'ABTKB'
                                  or actionBean.permohonan.kodUrusan.kod eq 'ABTBH'
                                  or actionBean.permohonan.kodUrusan.kod eq 'HLTE'
                                  or actionBean.permohonan.kodUrusan.kod eq 'SBSTL'
                                  or actionBean.permohonan.kodUrusan.kod eq 'ADMRL'}">
                          <p >
                              <label>Keluasan / Luas Terlibat :</label>                               
                              <s:text name="luas" id="luas" formatType="number" onblur="validateNum()"></s:text>
                               <span class="luas"></span>
                              
                          </p>
                          <p >
                              <label>&nbsp;</label>
                              (Luas Asal ${actionBean.hakmilik.luas})

                          </p>
                          <p class="wait">
                              <label>Unit Luas :</label>
                              <s:select name="kodLuas" id="kodLuas" >
                                  <s:option value="${actionBean.hakmilik.kodUnitLuas.kod}" >${actionBean.hakmilik.kodUnitLuas.nama}</s:option>
                                  <s:options-collection collection="${list.senaraiKodUOM}" label="nama" value="kod" />
                              </s:select>
                          </p>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'ABT-A'
                                  or actionBean.permohonan.kodUrusan.kod eq 'ABT-K'
                                  or actionBean.permohonan.kodUrusan.kod eq 'ABT'
                                  or actionBean.permohonan.kodUrusan.kod eq 'ABTKB'
                                  or actionBean.permohonan.kodUrusan.kod eq 'ABTBH'
                                  or actionBean.permohonan.kodUrusan.kod eq 'MCLL'
                                  or actionBean.permohonan.kodUrusan.kod eq 'PCT'
                                  or actionBean.permohonan.kodUrusan.kod eq 'SBSTL'
                                  or actionBean.permohonan.kodUrusan.kod eq 'TSSKL'
                                  or actionBean.permohonan.kodUrusan.kod eq 'SSKPL'
                                  or actionBean.permohonan.kodUrusan.kod eq 'KCL'}">
                          <p>
                              <label>Cukai Tanah (RM):</label>
                              <s:text name="cukaiBaru" id="cukai"/><span class="cukai" style="color:red; font-size:8pt; margin:0px;"></span>
                          </p>

                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSSKL'
                                  or actionBean.permohonan.kodUrusan.kod eq 'EUBS'
                                  or actionBean.permohonan.kodUrusan.kod eq 'SSKPL'}">
                          <p>
                              <label>Syarat Nyata :</label>

                              <s:text disabled="true" name="kodSyaratNyata" id="kodSyaratNyata" value=""/>&nbsp;<s:button name="cariKodSyaratNyata" value="Semak /Cari" id="cariKodSyaratNyata" class="btn" onclick="popupCarian('cariKodSyaratNyata&kodSyaratNyata=')" />
                          </p>
                          <p>
                              <label>Sekatan Kepentingan :</label>
                              <s:text disabled="true" name="kodSekatan" id="kodSekatan"/>&nbsp;<s:button name="cariKodSekatan" value="Semak /Cari" id="cariKodSekatan" class="btn" onclick="popupCarian('cariKodSekatan&kodSekatan=')"/>
                          </p>

                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSSKL'
                                  or actionBean.permohonan.kodUrusan.kod eq 'SSKPL'}">
                          <p>
                              <label>Kategori Tanah:</label>
                              <s:select name="katTanah" id="katTanah">
                                  <s:option value="">-- Sila Pilih --</s:option>
                                  <s:options-collection collection="${list.senaraiKodKategoriTanah}" label="nama" value="kod"/>
                              </s:select>
                          </p>
                    </c:if>

                    <%-- <table style="margin-left: auto; margin-right: auto;">
                         <tr>
                             <td>&nbsp;</td>
 <td>--%><div align="center">

                        <s:button name="simpanSingle" value="Simpan" class="btn" onclick="add(this.name, this.form)"/>

                        <s:button name="simpanKodSekatan" value="Tutup" id="simpanKodSekatan" class="btn" onclick="javascript:window.close();"/>


                    </div>
                    <%--        </td>
                        </tr>
                    </table>--%>
                </fieldset>
            </div>
        </s:form>


    </body>
</html>