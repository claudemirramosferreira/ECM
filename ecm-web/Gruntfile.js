/* global module */

module.exports = function (grunt) {
    // Project configuration.
    grunt.initConfig({
        folder:{
            app:'public_html/',
            dist:'dist/',
            wildfly:'C:/interactive/wildfly-8.1.0.Final/standalone/deployments/ecm-web.war/'
        },
        
        pkg: grunt.file.readJSON('package.json'),
        
        wiredep: {
            app:{
                src: ['<%= folder.app %>index.html']
            }
        },
        
        clean: {
            dist: {
                files: [{src: ['.tmp', '<%= folder.dist %>/**/**']}]
            },
            deploy:{
                files: [{src: ['<%= folder.wildfly %>**/**']}]
            }
        },
        copy:{
            default:{
                files:[{
                    expand: true, 
                    cwd: '<%= folder.app %>',
                    dest: '<%= folder.dist %>',
                    src:[
                        'index.html',
                        '404.html',
                        'favicon.ico',
                        'WEB-INF/**/**',
                        'css/**/**',
                        'documents/**/**',
                        'images/**/**',
                        'languages/**/**',
                        'views/**/**',
                        'external/**/**'
                        ]}
                ]
            },
            deploy:{
                files:[{
                    expand: true,
                    cwd:'<%= folder.dist %>',
                    dest: '<%= folder.wildfly %>',
                    src:['**/**/*.*']
                }]
            }
        },
        useminPrepare: {
            html: '<%= folder.app %>index.html',
            options: {flow: { html: {steps: { js: ['concat'/*, 'uglify'*/], css: ['concat', 'cssmin'] }, post: {}}}}
            
            
        },
        usemin: {
            html: '<%= folder.dist %>index.html'
        },
        filerev: {
            dist: {
                src: ['<%= folder.dist %>/scripts/**/**.js', '<%= folder.dist %>/css/**/**.css']
            }
        },
        
        watch: {
            bower: {
                files: ['bower.json', '<%= folder.app %>index.html'],
                tasks: ['wiredep']
            },
            scripts:{
                files: ['<%= folder.app %>scripts/controller/**/**/*.*'],
                tasks: ['jshint']
            }
        },
        jshint: {
            all: ['Gruntfile.js', '<%= folder.app %>scripts/controller/**/**/*.*']
        }
    });
    
    grunt.loadNpmTasks('grunt-wiredep');
    
    grunt.loadNpmTasks('grunt-contrib-clean');
    
    grunt.loadNpmTasks('grunt-contrib-copy');
    
    grunt.loadNpmTasks('grunt-usemin');
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-cssmin');
    grunt.loadNpmTasks('grunt-filerev');
    
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-contrib-jshint');
    
    grunt.registerTask('build', [
        'clean',
        'wiredep',
        //'jshint',
        'copy:default',
        'useminPrepare',
        'concat:generated',
        'cssmin:generated',
        //'uglify:generated',
        'filerev',
        'usemin',
        'clean:deploy',
        'copy:deploy'
    ]);
};
